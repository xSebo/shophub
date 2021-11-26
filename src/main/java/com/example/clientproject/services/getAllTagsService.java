package com.example.clientproject.services;

import com.example.clientproject.data.tags.Tags;
import org.springframework.stereotype.Service;

import java.util.List;

public interface getAllTagsService {
    List<Tags> findAll();
}