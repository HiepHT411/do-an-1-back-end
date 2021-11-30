package com.hoanghiep.da1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hoanghiep.da1.entity.Blog;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer>{

}
