package com.briup.apps.cms.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.briup.apps.cms.bean.BaseUser;
import com.briup.apps.cms.bean.BaseUserExample;
import com.briup.apps.cms.bean.UserRole;
import com.briup.apps.cms.bean.UserRoleExample;
import com.briup.apps.cms.bean.extend.BaseUserExtend;
import com.briup.apps.cms.dao.BaseUserMapper;
import com.briup.apps.cms.dao.UserRoleMapper;
import com.briup.apps.cms.dao.extend.BaseUserExtendMapper;
import com.briup.apps.cms.service.IBaseUserService;
import com.briup.apps.cms.utils.CustomerException;

/**
 * @program cms
 * @description
 * @author ME
 */
@Service
public class BaseUserServiceImpl implements IBaseUserService {

	@Resource
	private BaseUserMapper userMapper;
	@Resource
	private BaseUserExtendMapper baseExtend;
	@Resource
	private UserRoleMapper userRoleMapper;

	@Override
	public void register(BaseUser user) {
		userMapper.insert(user);
	}

	@Override
	public List<BaseUser> findAll() {
		BaseUserExample example = new BaseUserExample();
		return userMapper.selectByExample(example);
	}

	@Override
	public void deleteById(long id) throws CustomerException {
		BaseUser user = userMapper.selectByPrimaryKey(id);
		if (user == null) {
			throw new CustomerException("没有该用户");
		} else {
			userMapper.deleteByPrimaryKey(id);
		}
	}

	@Override
	public void saveOrUpdate(BaseUser user) throws CustomerException {
		if (user.getId() != null) {
			userMapper.updateByPrimaryKey(user);
		} else {
			BaseUserExample example = new BaseUserExample();
			example.createCriteria().andUsernameEqualTo(user.getUsername());
			List<BaseUser> list = userMapper.selectByExample(example);
			/*
			 * if (list != null) { throw new CustomerException("用户不能重复"); }
			 */
			userMapper.insert(user);
		}
	}

	@Override
	public BaseUserExtend findById(long id) {
		return baseExtend.selectById(id);
	}

	@Override
	public void setRoles(Long id, List<Long> roles) {
		// 根据userid查询自己的角色
		UserRoleExample example = new UserRoleExample();
		example.createCriteria().andUserIdEqualTo(id);
		// 用户角色关系,获取所有老的角色
		List<UserRole> list = userRoleMapper.selectByExample(example);
		List<Long> oldRoles = new ArrayList<>();
		Iterator<UserRole> iterator = list.iterator();
		while (iterator.hasNext()) {
			oldRoles.add(iterator.next().getRoleId());
		}
		// 依次判断新角色是否存在于list中，如果不存在则添加
		for (Long roleId : roles) {
			if (!oldRoles.contains(roleId)) {
				UserRole userRole = new UserRole();
				userRole.setRoleId(roleId);
				userRole.setUserId(id);
				userRoleMapper.insert(userRole);
			}
		}
		// [1,2,3] -> [1,2,3,4] 删除 3,4 => [1,2]
		// 依次判断老的角色是否存在于roles中，如果不存在则删除
		for (UserRole userRole : list) {
			if (!roles.contains(userRole.getRoleId())) {
				UserRoleExample example1 = new UserRoleExample();
				userRoleMapper.deleteByPrimaryKey(userRole.getId());
			}
		}
	}

	@Override
	public List<BaseUserExtend> cascadeRoleFindAll() {
		return baseExtend.findAll();
	}
}
