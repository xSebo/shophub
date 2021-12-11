package com.example.clientproject.service.Utils;

import com.example.clientproject.data.shops.ShopsRepo;
import com.example.clientproject.data.users.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class RemoveRedundantFiles {
    @Autowired
    private ShopsRepo shopRepo;
    @Autowired
    private UsersRepo userRepo;

    private final String dir = "./src/main/resources/static/imgs/uploaded/";

    private Set<String> getFilesInDir(){
        return Stream.of(new File(dir).listFiles())
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toSet());
    }

    private Set<String> getFilesInDb(){
        Set<String> curFiles = new HashSet<>();
        shopRepo.findAll().forEach(x -> {curFiles.add(x.getShopImage());
                                         curFiles.add(x.getShopBanner());});

        userRepo.findAll().forEach(x ->  curFiles.add(x.getUserProfilePicture()));

        return curFiles;
    }

    private Set<String> getDeletableFiles(){
        Set<String> deletableFiles = getFilesInDir();
        deletableFiles.removeAll(getFilesInDb());
        return deletableFiles;
    }

    public void deleteFiles(){
        for(String fileName:getDeletableFiles()) {
            File file = new File(dir + fileName);
            if(file.delete()){
                System.out.println("Deleted file " + fileName);
            }else{
                System.out.println("Failed to delete " + fileName);
            }
        }
    }

}
