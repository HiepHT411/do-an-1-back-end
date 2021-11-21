package com.hoanghiep.da1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hoanghiep.da1.entity.ERole;
import com.hoanghiep.da1.entity.Role;

@Repository
public interface Rolerepository extends JpaRepository<Role, Integer> {
	Optional<Role> findByName(ERole name);

}
