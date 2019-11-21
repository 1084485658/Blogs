package com.briup.apps.cms.service;

import java.util.List;

import com.briup.apps.cms.bean.BasePrivilege;
import com.briup.apps.cms.utils.CustomerException;
import com.briup.apps.cms.vm.PrivilegeTree;

/**
 * @program cms
 * @description
 * @author ME
 */
public interface IBasePrivilegeService {
	List<BasePrivilege> findAll();

	void saveOrUpdate(BasePrivilege basePrivilege);

	List<BasePrivilege> findByParentId(Long parentId);

	List<PrivilegeTree> findPrivilegeTree();

	//查询用户所有权限
	List<BasePrivilege> findByUserId(long id);
	
	void deleteById(Long id)throws CustomerException;
}
