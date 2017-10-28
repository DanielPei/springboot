package com.daniel.dao;

import org.apache.ibatis.annotations.Mapper;

import com.daniel.constant.BaseDao;
import com.daniel.entity.UUser;


 @Mapper
public interface UUserDao extends BaseDao<UUser, Long> {
	
	/**
	 * 增加对象
	 * @param obj
	 */
	public UUser findByName(String	 name);
}
