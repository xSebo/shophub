package com.example.clientproject.web.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateKeyInfoForm {
    private int shopId;
    private String shopName;
    private String shopDescription;
    private String bannerName;
    private String logoName;
}
