package com.hoanghiep.da1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hoanghiep.da1.entity.EProductType;
import com.hoanghiep.da1.entity.Type;

@Repository
public interface TypeRepository extends JpaRepository<Type, Integer> {

	Optional<Type> findByType(EProductType type);
}
