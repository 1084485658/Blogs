package com.briup.apps.cms.vm;

import java.util.List;

/**
 * @program cms
 * @description 用户角色
 * @author ME
 */
public class UserRoleVM {
	private Long id;
	private List<Long> roles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Long> getRoles() {
		return roles;
	}

	public void setRoles(List<Long> roles) {
		this.roles = roles;
	}
}
