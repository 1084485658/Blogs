package com.briup.apps.cms.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.cms.bean.BasePrivilege;
import com.briup.apps.cms.service.IBasePrivilegeService;
import com.briup.apps.cms.utils.Message;
import com.briup.apps.cms.utils.MessageUtil;
import com.briup.apps.cms.vm.PrivilegeTree;

import io.swagger.annotations.ApiOperation;

/**
 * @program cms
 * @description
 * @author ME
 */
@RestController
@RequestMapping("/privilege")
public class PrivilegeController {
	@Autowired
	private IBasePrivilegeService basePrivilegeService;

	@GetMapping("/findAll")
	public Message findAll() {
		List<BasePrivilege> list = basePrivilegeService.findAll();
		return MessageUtil.success(list);
	}

	@ApiOperation(value = "保存或更新")
	@PostMapping(value = "saveOrUpdate")
	public Message saveOrUpdate(BasePrivilege privilege) {
		basePrivilegeService.saveOrUpdate(privilege);
		return MessageUtil.success("更新成功");
	}
	
	@GetMapping("/findByParentId")
	public Message findByParentId(Long id) {
		List<BasePrivilege> list = basePrivilegeService.findByParentId(id);
		return MessageUtil.success(list);
	}

	@GetMapping("/findPrivilegeTree")
	public Message findPrivilegeTree() {
		List<PrivilegeTree> list = basePrivilegeService.findPrivilegeTree();
		return MessageUtil.success(list);
	}
	@GetMapping("/deleteById")
	public Message deleteById(Long id) {
		basePrivilegeService.deleteById(id);
		return MessageUtil.success("删除成功");
	}
}
