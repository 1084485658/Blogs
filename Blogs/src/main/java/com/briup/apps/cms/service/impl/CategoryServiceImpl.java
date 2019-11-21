package com.briup.apps.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.briup.apps.cms.bean.Category;
import com.briup.apps.cms.bean.CategoryExample;
import com.briup.apps.cms.dao.CategoryMapper;
import com.briup.apps.cms.service.ICategoryService;
import com.briup.apps.cms.utils.CustomerException;

/**
 * @program cms
 * @description
 * @author ME
 */
@Service
public class CategoryServiceImpl implements ICategoryService {
	@Resource
	private CategoryMapper categoryMapper;

	@Override
	public List<Category> findAll() {
		CategoryExample example = new CategoryExample();
		return categoryMapper.selectByExample(example);
	}

	@Override
	public void saveOrUpdate(Category category) throws CustomerException {
		if (category.getId() != null) {
			categoryMapper.updateByPrimaryKey(category);
		} else {
			CategoryExample example = new CategoryExample();
			example.createCriteria().andNameEqualTo(category.getName());
			List<Category> list = categoryMapper.selectByExample(example);
			if (list.size()>0) {
				throw new CustomerException("栏目重复");
			}
			categoryMapper.insert(category);
		}
	}

	@Override
	public void deleteById(long id) {
		Category c = categoryMapper.selectByPrimaryKey(id);
		if (c != null) {
			categoryMapper.deleteByPrimaryKey(id);
		} else {
			throw new CustomerException("没有该栏目");
		}
	}

	@Override
	public void batchDelete(long[] ids) throws CustomerException {
		for (long id : ids) {
			this.deleteById(id);
		}

	}
}
