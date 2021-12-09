package com.example.clientproject.web.restControllers;

import com.example.clientproject.data.shops.Shops;
import com.example.clientproject.data.shops.ShopsRepo;
import com.example.clientproject.data.tags.Tags;
import com.example.clientproject.data.tags.TagsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class ShopSearch {
    @Autowired
    ShopsRepo shopsRepo;

    @Autowired
    TagsRepo tagsRepo;

    @GetMapping("/shop/search")
    public String searchShops(@RequestParam(value = "q", required = false) String query,
                              @RequestParam(value = "p", required = false) Integer page,
                              @RequestParam(value = "t", required = false) List<String> tags){
        final Integer ITEMS_PER_PAGE = 6;

        //Get all the active shops
        List<Shops> allShops = shopsRepo.findActiveShops();

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

        //Convert to required format


        //Add details such as the amount of rewards

        return allShops.toString();

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
