package com.example.clientproject.web.restControllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class BusinessInfoExtractor {

    /**
     *
     * @param url request param of url given
     * @return result of calling getHTMLMeta
     * @throws Exception
     */
    @GetMapping("/infoExtract")
    public HashMap<String, String> testIndex(@RequestParam(value = "url") String url) throws Exception{
        System.out.println(url);
        return getHTMLMeta(url);
    }

    /**
     *
     * @param urlToRead Must include "https://" the url of the shopify store to extract from
     * @return returns a hashmap of key values that relate to the given url
     * @throws Exception
     */
    public static HashMap<String, String> getHTMLMeta(String urlToRead) throws Exception {
        HashMap<String, String> metaTags = new HashMap<>();
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()))) {
            for (String line; (line = reader.readLine()) != null; ) {
                if(line.contains("<meta ")) {
                    String content = "";
                    Pattern pattern = Pattern.compile("content=\"(.*?)\"");
                    Matcher matcher = pattern.matcher(line);
                    if (matcher.find())
                    {
                        content = matcher.group(1);
                    }
                    if(!content.equalsIgnoreCase("")){
                        String name = "";
                        pattern = Pattern.compile("property=\"og:(.*?)\"");
                        matcher = pattern.matcher(line);
                        if (matcher.find())
                        {
                            name = matcher.group(1);
                        }else{
                            pattern = Pattern.compile("name=\"(.*?)\"");
                            matcher = pattern.matcher(line);
                            if (matcher.find()) {
                                name = matcher.group(1);
                            }
                        }

                        if(!name.equalsIgnoreCase("")){
                            metaTags.put(name, content);
                        }
                    }
                }
            }
        }
        return metaTags;
    }

}
