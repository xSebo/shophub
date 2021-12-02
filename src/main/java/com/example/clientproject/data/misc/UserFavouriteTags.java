package com.example.clientproject.data.misc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFavouriteTags {
    private long userFavouriteTagId;
    private long userId;
    private long tagId;
}
