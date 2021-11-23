package com.example.clientproject.services;

import com.example.clientproject.data.tags.Tags;
import com.example.clientproject.data.tags.TagsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class getAllTagsServiceStatic implements getAllTagsService{

    private TagsRepo tagsRepo;

    @Autowired
    public getAllTagsServiceStatic(TagsRepo aRepo) {
        tagsRepo = aRepo;
    }


    public List<Tags> findAll() {
        System.out.println("Service test");
        return tagsRepo.findAll();
    }
}
