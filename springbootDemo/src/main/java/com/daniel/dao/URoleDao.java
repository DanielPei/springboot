package com.daniel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.daniel.constant.BaseDao;
import com.daniel.entity.URole;


@Mapper
public interface URoleDao extends BaseDao<URole, Long> {
	
	/**
	 * 根据用户ID获取该用户所在组的角色权限
	 * @param obj
	 */
	public List<URole> findRoleByUid(Long	 obj);
}
