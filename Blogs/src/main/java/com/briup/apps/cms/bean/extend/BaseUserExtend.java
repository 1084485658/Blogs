package com.briup.apps.cms.bean.extend;

import java.util.List;

import com.briup.apps.cms.bean.BaseRole;
import com.briup.apps.cms.bean.BaseUser;

/**
 *@program cms
 *@description 用户拓展
 *@author ME
 */
public class BaseUserExtend extends BaseUser{
	private List<BaseRole> roles;

	public List<BaseRole> getRoles() {
		return roles;
	}

	public void setRoles(List<BaseRole> roles) {
		this.roles = roles;
	}
	
}
