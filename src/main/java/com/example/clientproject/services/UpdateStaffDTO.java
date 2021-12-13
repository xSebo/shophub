package com.example.clientproject.services;

import com.example.clientproject.web.forms.UpdateStaffForm;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class UpdateStaffDTO {
    String userEmail;
    int shopId;

    public UpdateStaffDTO(UpdateStaffForm usf){
        this(
                usf.getEmail(),
                usf.getShopId()
        );
    }
}
