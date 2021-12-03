package com.example.clientproject.web.forms;

import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStampboardForm {
    public String colour;

    public String rewardMapping;

    public Integer stampboardSize;

    public Integer shopId;

    public String iconFilePath;
}
