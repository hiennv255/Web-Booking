package com.vti.bookinghospitalbackend.databse;


import com.vti.bookinghospitalbackend.model.ERole;
import com.vti.bookinghospitalbackend.model.Role;
import com.vti.bookinghospitalbackend.model.User;
import com.vti.bookinghospitalbackend.repository.RoleRepository;
import com.vti.bookinghospitalbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class Database {
//https://www.devglan.com/spring-boot/spring-boot-mongodb-configuration
    @Autowired
    PasswordEncoder encoder;

    @Bean
    CommandLineRunner initDatabase(RoleRepository roleRepository,
                                   UserRepository userRepository) {
        return new CommandLineRunner()
        {
            @Override
            public void run(String... args) throws Exception {
              //  new ConectorSQL().mostrarDatos();

                Role role1 = new Role();
                role1.setId(1);
                role1.setName(ERole.ROLE_ADMIN);

                Role role2 = new Role();
                role2.setId(2);
                role2.setName(ERole.ROLE_DOCTOR);

                Role role3 = new Role();
                role3.setId(3);
                role3.setName(ERole.ROLE_HOSPITAL);

                Role role4 = new Role();
                role4.setId(4);
                role4.setName(ERole.ROLE_CLINIC);

                Role role5 = new Role();
                role4.setId(5);
                role4.setName(ERole.ROLE_USER);

                if(roleRepository.findAll().size()==0){
                    roleRepository.save(role1);
                    roleRepository.save(role2);
                    roleRepository.save(role3);
                    roleRepository.save(role4);
                    roleRepository.save(role5);

                }

            // Generate user super admin
                if(userRepository.findAll().size()==0){
                    User admin = new User();
                    admin.setId(1l);
                    admin.setUserName("admin_VIP");
                    admin.setEmail("bg.hien@gmail.com");
                    admin.setFullName("Admin Vip");
                    admin.setCreatedTime(new Date());
                    Set<Role> roles = new HashSet<>();
                    admin.setPassword(encoder.encode("123Abc@#"));
                    Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(adminRole);
                    admin.setRoles(roles);
                    userRepository.save(admin);
                }
        }};
    }
}