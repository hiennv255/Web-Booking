package com.vti.bookinghospitalbackend.repository;



import com.vti.bookinghospitalbackend.model.ERole;
import com.vti.bookinghospitalbackend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);

}
