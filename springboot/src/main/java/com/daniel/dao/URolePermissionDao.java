package com.daniel.dao;

import org.apache.ibatis.annotations.Mapper;

import com.daniel.constant.BaseDao;
import com.daniel.entity.URolePermission;


 @Mapper
public interface URolePermissionDao extends BaseDao<URolePermission, Long> {
	
	/**
	 * 增加对象
	 * @param obj
	 */
	//public void add(SysMessageTep	 obj);
}
