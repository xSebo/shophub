package com.example.clientproject.web.restControllers;

import com.example.clientproject.data.shops.Shops;
import com.example.clientproject.data.shops.ShopsRepo;
import com.example.clientproject.data.tags.Tags;
import com.example.clientproject.data.tags.TagsRepo;
import com.example.clientproject.data.users.Users;
import com.example.clientproject.service.Utils.JWTUtils;
import com.example.clientproject.services.DashboardStampLoader;
import com.example.clientproject.services.RecommendationGenerator;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class ShopSearch {
    @Autowired
    ShopsRepo shopsRepo;

    @Autowired
    TagsRepo tagsRepo;

    @Autowired
    RecommendationGenerator recommendationGenerator;

    @Autowired
    DashboardStampLoader stampLoader;

    @Autowired
    JWTUtils jwtUtils;

    @GetMapping("/shop/search")
    public String searchShops(@RequestParam(value = "q", required = false) String query,
                              @RequestParam(value = "p", required = false) Integer page,
                              @RequestParam(value = "t", required = false) List<String> tags,
                              HttpSession session) throws Exception {
        final Integer ITEMS_PER_PAGE = 6;

        //Get all the active shops
        List<Shops> allShops = shopsRepo.findActiveShops();

        Optional<Users> user = jwtUtils.getLoggedInUserRow(session);
        if(user.isPresent()){
            Map<String,Object> userInfo = stampLoader.getData((int) user.get().getUserId());

            //Filter off shops that the user has stamps in
            List<Shops> purchased = (List<Shops>)userInfo.get("purchased");
            allShops = allShops
                    .stream()
                    .filter(s -> !purchased.contains(s))
                    .collect(Collectors.toList());

            //Filter off shops the user has favourited
            List<Shops> favourited = (List<Shops>)userInfo.get("favourited");
            allShops = allShops
                    .stream()
                    .filter(s -> !favourited.contains(s))
                    .collect(Collectors.toList());
        }


        //Filter the shops using the query provided
        if(query != null){
            allShops = allShops
                    .stream()
                    .filter(s -> s.getShopName().toLowerCase(Locale.ROOT).strip().contains(query.toLowerCase(Locale.ROOT).strip()))
                    .collect(Collectors.toList());
        }

        //Filter using the tags provided
        if(tags!=null){
            List<Long> validTagIds = new ArrayList<>();
            for (String t : tags){
                Optional<Tags> tagsOptional = tagsRepo.findByTagNameIgnoreCase(t);
                if(tagsOptional.isPresent()){
                    Long tagId = tagsOptional.get().getTagId();
                    if (!validTagIds.contains(tagId)){
                        validTagIds.add(tagId);
                    }
                }
            }
            List<Shops> validShops = new ArrayList<>();
            for (Shops s : allShops){
                boolean match = false;
                for (Tags t : s.getShopTags()){
                    if (validTagIds.contains(t.getTagId())){
                        match = true;
                        break;
                    }
                }
                if (match){
                    validShops.add(s);
                }
            }
            allShops = validShops;
        }

        //Paginate
        boolean hasNextPage = false;
        if (allShops.size() > ITEMS_PER_PAGE){
            if(page==null){
                page = 1;
            }
            List<List<Shops>> pages = getPages(allShops, ITEMS_PER_PAGE);
            if(page > pages.size()){
                page = 1;
            }
            if (pages.size() >= page){
                allShops = pages.get(page-1);
            }
            if (pages.size() >= page + 1){
                hasNextPage = true;
            }
        }

        //Sort in order of relevance
        allShops = recommendationGenerator.getRecommendations(session, allShops);

        //Convert to required format
        List<HashMap<String, String>> formattedShops = new ArrayList<>();
        for(Shops shop : allShops){
            HashMap<String,String> data = new HashMap<>();
            data.put("name",shop.getShopName());
            data.put("banner",shop.getShopBanner());
            data.put("id", String.valueOf(shop.getShopId()));
            data.put("category",shop.getCategory().getCategoryName());
            data.put("website",shop.getShopWebsite());
            Integer reward_count = shop.getStampBoard().getRewards().size();
            data.put("reward_count",String.valueOf(reward_count));
            if(reward_count != 0){
                data.put("next_reward_name",shop.getStampBoard().getRewards().get(0).getRewardName());
                data.put("next_reward_pos",String.valueOf(shop.getStampBoard().getRewards().get(0).getRewardStampLocation()));
            }else{
                data.put("next_reward_name","No Rewards");
            }
            formattedShops.add(data);
        }

        Map<String,Object> returnMap = new HashMap<>();
        returnMap.put("shops",formattedShops);
        returnMap.put("hasNextPage", hasNextPage);

        Gson gson = new Gson();
        String json = gson.toJson(returnMap);

        return json;

    }

    public <T> List<List<T>> getPages(Collection<T> c, Integer pageSize) {
        if (c == null)
            return Collections.emptyList();
        List<T> list = new ArrayList<T>(c);
        if (pageSize == null || pageSize <= 0 || pageSize > list.size())
            pageSize = list.size();
        int numPages = (int) Math.ceil((double)list.size() / (double)pageSize);
        List<List<T>> pages = new ArrayList<List<T>>(numPages);
        for (int pageNum = 0; pageNum < numPages;)
            pages.add(list.subList(pageNum * pageSize, Math.min(++pageNum * pageSize, list.size())));
        return pages;
    }
}
