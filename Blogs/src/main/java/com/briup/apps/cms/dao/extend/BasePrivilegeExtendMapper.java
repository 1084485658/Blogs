package com.briup.apps.cms.dao.extend;

import java.util.List;

import com.briup.apps.cms.bean.BasePrivilege;
import com.briup.apps.cms.vm.PrivilegeTree;

/**
 * @program cms
 * @description
 * @author ME
 */
public interface BasePrivilegeExtendMapper {
	List<PrivilegeTree> selectAll();

	List<BasePrivilege> selectByParentId(long id);

	List<BasePrivilege> selectByRoleId(long id);

	List<BasePrivilege> selectByUserId(long id);
}
