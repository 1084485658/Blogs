package com.briup.apps.cms.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.briup.apps.cms.bean.BaseRole;
import com.briup.apps.cms.bean.BaseRoleExample;
import com.briup.apps.cms.bean.RolePrivilege;
import com.briup.apps.cms.bean.RolePrivilegeExample;
import com.briup.apps.cms.bean.extend.BaseRoleExtend;
import com.briup.apps.cms.dao.BaseRoleMapper;
import com.briup.apps.cms.dao.RolePrivilegeMapper;
import com.briup.apps.cms.dao.extend.BaseRoleExtendMapper;
import com.briup.apps.cms.service.IBaseRoleService;
import com.briup.apps.cms.utils.CustomerException;

/**
 * @program cms
 * @description
 * @author ME
 */
@Service
public class BaseRoleServiceImpl implements IBaseRoleService {
	@Resource
	private BaseRoleMapper baseRoleMapper;
	@Resource
	private BaseRoleExtendMapper baseRoleExtendMapper;
	@Resource
	private RolePrivilegeMapper rolePrivilegeMapper;

	@Override
	public void authorization(long roleId, List<Long> privilegeIds) {
		RolePrivilegeExample example = new RolePrivilegeExample();
		example.createCriteria().andRoleIdEqualTo(roleId);
		List<RolePrivilege> list = rolePrivilegeMapper.selectByExample(example);
		// 把list换成privilegeIds的集合
		List<Long> old_privilegeIds = new ArrayList<>();
		for (RolePrivilege rp : list) {
			old_privilegeIds.add(rp.getPrivilegeId());
		}
		// 依次判断privilegeIds 是否存在old_privilegeIds，如果不在则插入
		for (Long privilegeId : privilegeIds) {
			if (!old_privilegeIds.contains(privilegeId)) {
				RolePrivilege rp = new RolePrivilege();
				rp.setRoleId(roleId);
				rp.setPrivilegeId(privilegeId);
				rolePrivilegeMapper.insert(rp);
			}
		}
		// 依次判断 old_privilegeIds 是否存在privilegeIds，如果不存在删除
		for (Long privilegeId : old_privilegeIds) {
			if (!privilegeIds.contains(privilegeId)) {
				example.clear();
				example.createCriteria().andRoleIdEqualTo(roleId).andPrivilegeIdEqualTo(privilegeId);
				rolePrivilegeMapper.deleteByExample(example);
			}
		}
	}

	@Override
	public List<BaseRole> findAll() {
		BaseRoleExample example = new BaseRoleExample();
		return baseRoleMapper.selectByExample(example);
	}

	@Override
	public List<BaseRoleExtend> cascadePrivilegeFindAll() {
		return baseRoleExtendMapper.selectAll();
	}

	@Override
	public void saveOrUpdate(BaseRole baseRole) throws CustomerException {
		if (baseRole.getId() != null) {
			baseRoleMapper.updateByPrimaryKey(baseRole);
		} else {
			baseRoleMapper.insert(baseRole);
		}

	}

	@Override
	public void deleteById(long id) throws CustomerException {
		BaseRole key = baseRoleMapper.selectByPrimaryKey(id);
		if (key == null) {
			throw new CustomerException("没有改用户");
		} else {
			baseRoleMapper.deleteByPrimaryKey(id);
		}
	}
}
