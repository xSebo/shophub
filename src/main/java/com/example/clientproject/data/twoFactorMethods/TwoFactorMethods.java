package com.example.clientproject.data.twoFactorMethods;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * TwoFactorMethods Entity
 * Contains Getters, Setters, and constructors thanks to Lombok
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TwoFactorMethods {
    @Id
    private long twoFactorMethodId;
    private String twoFactorMethodName;
}
