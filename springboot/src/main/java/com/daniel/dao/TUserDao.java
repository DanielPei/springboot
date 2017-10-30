package com.daniel.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daniel.entity.User;

public interface TUserDao extends JpaRepository<User, Integer>{

	User findByUsername(String name);
}
