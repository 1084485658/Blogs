package com.briup.apps.cms.service;

import java.util.List;

import com.briup.apps.cms.bean.BaseUser;
import com.briup.apps.cms.bean.extend.BaseUserExtend;
import com.briup.apps.cms.utils.CustomerException;

/**
 *@program cms
 *@description 
 *@author ME
 */
public interface IBaseUserService {
	
	BaseUserExtend findById(long id);
	
	void register(BaseUser user);
	List <BaseUser> findAll();
	List <BaseUserExtend> cascadeRoleFindAll();
	
	void saveOrUpdate(BaseUser user) throws CustomerException;
	void deleteById(long id)throws CustomerException;

	void setRoles(Long id, List<Long> roles);
}
