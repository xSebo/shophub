package com.example.clientproject.web.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSocialsForm {
    private int shopId;
    private String instagram;
    private String facebook;
    private String twitter;
    private String tiktok;
    private String shopUrl;

}
