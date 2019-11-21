package com.briup.apps.cms.vm;

import java.util.List;

import com.briup.apps.cms.bean.BasePrivilege;

/**
 *@program cms
 *@description 
 *@author ME
 */
public class PrivilegeTree extends BasePrivilege{
	  private List<BasePrivilege> children;

	    public List<BasePrivilege> getChildren() {
	        return children;
	    }

	    public void setChildren(List<BasePrivilege> children) {
	        this.children = children;
	    }
}
