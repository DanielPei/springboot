package com.daniel.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daniel.entity.Permission;

public interface TPermissionDao extends JpaRepository<Permission, Integer>{

}
