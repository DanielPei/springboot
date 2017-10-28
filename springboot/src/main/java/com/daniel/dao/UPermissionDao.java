package com.daniel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.daniel.constant.BaseDao;
import com.daniel.entity.UPermission;


 @Mapper
public interface UPermissionDao extends BaseDao<UPermission, Long> {

	
	
	/**
	 * 根据用户id获取用户权限
	 * @param obj
	 */
	 List<UPermission> findPermissionByUid(Long id);
}
