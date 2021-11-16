package com.example.clientproject.data.adminTypes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AdminTypes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long adminTypeID;
    private String adminTypeName;
}
