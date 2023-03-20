package com.springboot.fundamentals.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.fundamentals.entity.UserPost;

//@Repository
public interface UserPostRepository extends JpaRepository<UserPost, Long> {

}
