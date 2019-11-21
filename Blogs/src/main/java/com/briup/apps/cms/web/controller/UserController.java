package com.briup.apps.cms.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.cms.bean.BaseUser;
import com.briup.apps.cms.bean.extend.BaseUserExtend;
import com.briup.apps.cms.service.IBaseUserService;
import com.briup.apps.cms.utils.Message;
import com.briup.apps.cms.utils.MessageUtil;
import com.briup.apps.cms.vm.UserRoleVM;
import com.briup.apps.cms.vm.UserVM;

import io.swagger.annotations.ApiOperation;

/**
 * @program cms
 * @description
 * @author ME
 */
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private IBaseUserService userService;

	@ApiOperation(value = "登录")
	@PostMapping("/login")
	public Message login(@RequestBody UserVM userVM) {
		// 1 验证用户身份
		// 2产生一个token，缓存起来
		// 3返回
		Map<String, String> map = new HashMap<>();
		map.put("token", "admin-token");
		return MessageUtil.success(map);
	}

	@ApiOperation("退出")
	@PostMapping("/logout")
	public Message logout() {
		// 1将缓存中的token移除
		// 2其他
		return MessageUtil.error("success");
	}

	@ApiOperation("通过token获取用户信息")
	@GetMapping("/info")
	public Message info(String token) {
		BaseUserExtend user = userService.findById(1l);

		return MessageUtil.success(user);
	}

	@GetMapping("/register")
	public String register(BaseUser user) {
		userService.register(user);
		return "成功";
	}

	@GetMapping("/findAll")
	public Message findAll() {
		List<BaseUser> list = userService.findAll();
		return MessageUtil.success(list);
	}

	@PostMapping("/saveOrUpdate")
	public Message saveOrUpdate(BaseUser user) {
		userService.saveOrUpdate(user);
		return MessageUtil.success("成功");
	}

	@GetMapping("/deleteById")
	public Message deleteById(Long id) {
		userService.deleteById(id);
		return MessageUtil.success("成功");
	}

	@ApiOperation(value = "设置权限")
	@PostMapping(value ="setRoles")
	public Message setRoles(UserRoleVM userRoleVM) {
		System.out.println(userRoleVM);
		userService.setRoles(userRoleVM.getId(), userRoleVM.getRoles());
		return MessageUtil.success("设置成功");
	}
	
	@GetMapping("/cascadeRoleFindAll")
	public Message cascadeRoleFindAll() {
		List<BaseUserExtend> list = userService.cascadeRoleFindAll();
		return MessageUtil.success(list);
	}

}
