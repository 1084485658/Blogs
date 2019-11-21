package com.briup.apps.cms.service;

import java.util.List;

import com.briup.apps.cms.bean.BaseRole;
import com.briup.apps.cms.bean.extend.BaseRoleExtend;
import com.briup.apps.cms.utils.CustomerException;

/**
 * @program cms
 * @description
 * @author ME
 */
public interface IBaseRoleService {
	
	// 授权
	void authorization(long roleId, List<Long> privilegeIds);

	// 查询所有角色
	List<BaseRole> findAll();

	// 查询角色级联权限

	List<BaseRoleExtend> cascadePrivilegeFindAll();

	// 保存或更新角色信息

	void saveOrUpdate(BaseRole baseRole) throws CustomerException;

	// @Description: 通过id删除角色信息

	void deleteById(long id) throws CustomerException;
}
