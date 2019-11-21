package com.briup.apps.cms.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.cms.bean.Category;
import com.briup.apps.cms.service.ICategoryService;
import com.briup.apps.cms.utils.Message;
import com.briup.apps.cms.utils.MessageUtil;

/**
 *@program cms
 *@description 
 *@author ME
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private ICategoryService Icategory;
	
	@GetMapping("/findAll")
	public Message findAll() {
		List<Category> list = Icategory.findAll();
		return MessageUtil.success(list);
		}
	@PostMapping("/saveOrUpdate")
	public Message SaveOrUpdate(Category category) {
		Icategory.saveOrUpdate(category);
		return MessageUtil.success("更新成功");
		}
	@GetMapping("/deleteById")
	public Message deleteById(Long id) {
		Icategory.deleteById(id);
		return MessageUtil.success("删除成功");
		}
	@PostMapping("/batchDelete")
	public Message batchDelete(@RequestBody long[] ids) {
		Icategory.batchDelete(ids);
		return MessageUtil.success("删除成功");
		}
	
	}
