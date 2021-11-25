package com.example.clientproject.services;

import com.example.clientproject.data.tags.Tags;
import com.example.clientproject.data.tags.TagsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class getAllTagsServiceStatic implements getAllTagsService{

    @Autowired
    TagsRepo tagsRepo;

    @Override
    public List<Tags> findAll() {
        return tagsRepo.findAll();
    }
}
