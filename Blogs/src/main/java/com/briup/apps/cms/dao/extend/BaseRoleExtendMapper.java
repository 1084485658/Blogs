package com.briup.apps.cms.dao.extend;

import java.util.List;

import com.briup.apps.cms.bean.BaseRole;
import com.briup.apps.cms.bean.extend.BaseRoleExtend;

/**
 * @program cms
 * @description
 * @author ME
 */
public interface BaseRoleExtendMapper {
	List<BaseRole> selectByUserId(long id);

	List<BaseRoleExtend> selectAll();
}
