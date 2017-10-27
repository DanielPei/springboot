package com.daniel.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daniel.entity.Role;

public interface TRoleDao extends JpaRepository<Role, Integer>{

}
