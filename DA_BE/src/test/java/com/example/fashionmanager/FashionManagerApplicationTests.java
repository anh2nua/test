package com.example.fashionmanager;

import com.example.fashionmanager.enums.RoleEnums;
import com.example.fashionmanager.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootTest
class FashionManagerApplicationTests {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

    @Test
    void contextLoads() {



        System.out.println(roleRepository.findAllByRoleNameInAndActiveIsTrueAndDeletedIsFalse(List.of(RoleEnums.ROLE_ADMIN_BILL,RoleEnums.ROLE_ADMIN_MAKETING)));
    }

}
