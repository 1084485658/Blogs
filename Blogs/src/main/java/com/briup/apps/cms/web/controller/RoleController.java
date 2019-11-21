package com.briup.apps.cms.web.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.cms.bean.BaseRole;
import com.briup.apps.cms.bean.extend.BaseRoleExtend;
import com.briup.apps.cms.service.IBaseRoleService;
import com.briup.apps.cms.utils.Message;
import com.briup.apps.cms.utils.MessageUtil;

import io.swagger.annotations.ApiOperation;

/**
 * @program cms
 * @description
 * @author ME
 */
@RestController
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private IBaseRoleService baseRoleService;

	@GetMapping("/cascadePrivilegeFindAll")
	public Message cascadePrivilegeFindAll() {
		List<BaseRoleExtend> list = baseRoleService.cascadePrivilegeFindAll();
		return MessageUtil.success(list);
	}

	@GetMapping("/deleteById")
	public Message deleteById(Long id) {
		baseRoleService.deleteById(id);
		return MessageUtil.success("删除成功");
	}

	@GetMapping("/findAll")
	public Message findAll() {
		List<BaseRole> list = baseRoleService.findAll();
		return MessageUtil.success(list);
	}

	@PostMapping("/saveOrUpdate")
	public Message saveOrUpdate(BaseRole baseRole) {
		baseRoleService.saveOrUpdate(baseRole);
		return MessageUtil.success("更新成功");
	}

	@ApiOperation(value = "为角色授权")
	@PostMapping(value = "authorization")
	public Message authorization(long id, Long[] privileges) {
		List<Long> ids = Arrays.asList(privileges);
		baseRoleService.authorization(id, ids);
		return MessageUtil.success("授权成功");
	}

}
