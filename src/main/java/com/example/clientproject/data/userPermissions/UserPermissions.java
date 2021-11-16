package com.example.clientproject.data.userPermissions;

import com.example.clientproject.data.adminTypes.AdminTypes;
import com.example.clientproject.data.shops.Shops;
import com.example.clientproject.data.users.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserPermissions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userPermissionId;

    @ManyToOne
    @JoinColumn(name="User_Id", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name="Shop_Id", nullable = true)
    private Shops shop;

    @ManyToOne
    @JoinColumn(name="Admin_Type_Id", nullable = false)
    private AdminTypes adminType;
}