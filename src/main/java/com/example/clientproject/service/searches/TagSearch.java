package com.example.clientproject.service.searches;

import com.example.clientproject.service.dtos.TagsDTO;

import java.util.List;


public interface TagSearch {
    List<TagsDTO> findAll();
}
