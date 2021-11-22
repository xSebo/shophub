package com.example.clientproject.services;

import com.example.clientproject.data.tags.Tags;
import com.example.clientproject.data.tags.TagsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class getAllTagsServiceStatic implements getAllTagsService{

    @Autowired
    public getAllTagsServiceStatic(TagsRepo aRepo){
        tagsRepo = aRepo;
    }
    TagsRepo tagsRepo;

    public List<Tags> getAllTags() {
        return tagsRepo.findAll();
    }
}
