package com.briup.apps.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.briup.apps.cms.bean.BasePrivilege;
import com.briup.apps.cms.bean.BasePrivilegeExample;
import com.briup.apps.cms.dao.BasePrivilegeMapper;
import com.briup.apps.cms.dao.extend.BasePrivilegeExtendMapper;
import com.briup.apps.cms.dao.extend.BaseUserExtendMapper;
import com.briup.apps.cms.service.IBasePrivilegeService;
import com.briup.apps.cms.utils.CustomerException;
import com.briup.apps.cms.vm.PrivilegeTree;

/**
 * @program cms
 * @description
 * @author ME
 */
@Service
public class BasePrivilegeServiceImpl implements IBasePrivilegeService {
	@Resource
	private BasePrivilegeMapper basePrivilegeMapper;
	@Resource
	private BasePrivilegeExtendMapper basePrivilegeExtendMapper;

	@Override
	public List<BasePrivilege> findAll() {
		BasePrivilegeExample example = new BasePrivilegeExample();
		return basePrivilegeMapper.selectByExample(example);
	}

	@Override
	public void saveOrUpdate(BasePrivilege basePrivilege) {
		if (basePrivilege.getId() != null) {
			basePrivilegeMapper.updateByPrimaryKey(basePrivilege);
		} else {
			basePrivilegeMapper.insert(basePrivilege);
		}
	}

	@Override
	public List<BasePrivilege> findByParentId(Long parentId) {
		if (parentId == null) {
			BasePrivilegeExample example = new BasePrivilegeExample();
			example.createCriteria().andParentIdIsNull();
			return basePrivilegeMapper.selectByExample(example);
		} else {
			BasePrivilegeExample example = new BasePrivilegeExample();
			example.createCriteria().andParentIdEqualTo(parentId);
			return basePrivilegeMapper.selectByExample(example);
		}
	}
	@Override
	public List<PrivilegeTree> findPrivilegeTree() {
		return basePrivilegeExtendMapper.selectAll();
	}

	@Override
	public List<BasePrivilege> findByUserId(long id) {
		return basePrivilegeExtendMapper.selectByUserId(id);
	}
	
	@Override
	public void deleteById(Long id) throws CustomerException {
		BasePrivilege key = basePrivilegeMapper.selectByPrimaryKey(id);
		if(key==null) {
			throw new CustomerException("没有该用户");
		}else {
			basePrivilegeMapper.deleteByPrimaryKey(id);
		}
	}
}
