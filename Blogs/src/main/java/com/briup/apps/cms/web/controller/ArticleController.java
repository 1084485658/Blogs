package com.briup.apps.cms.web.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.cms.bean.Article;
import com.briup.apps.cms.bean.extend.ArticleExtend;
import com.briup.apps.cms.service.IArticleService;
import com.briup.apps.cms.utils.Message;
import com.briup.apps.cms.utils.MessageUtil;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @program cms
 * @description
 * @author ME
 */
@Validated
@RestController
@RequestMapping("/article")
public class ArticleController {
	@Autowired
	private IArticleService articleService;

	@GetMapping(value = "/findAll")
	public Message findAll() {
		List<Article> list = articleService.findAll();
		return MessageUtil.success(list);
	}

	@GetMapping(value = "/cascadefindAll")
	public Message cascadefindAll() {
		List<ArticleExtend> list = articleService.cascadefindAll();
		return MessageUtil.success(list);
	}

	@GetMapping(value = "/findById")
	public Message findById(long id) {
		ArticleExtend extend = articleService.findById(id);
		return MessageUtil.success(extend);
	}
	@ApiOperation(value = "保存或更新")
	@PostMapping(value = "/saveOrUpdate")
	public Message saveOrUpdate(
			@ApiParam(value = "编号")@RequestParam(value = "id",required = false)Long id,
			@NotNull@ApiParam(value = "标题",required = true)@RequestParam(value = "title")String title, 
			@NotNull@ApiParam(value = "内容",required = true)@RequestParam(value = "content")String content, 
		//	@ApiParam(value = "原内容",required = true)@RequestParam(value = "source")String source, 
		//	@NotNull@ApiParam(value = "作者id",required = true)@RequestParam(value = "authorId")long authorId, 
			@NotNull@ApiParam(value = "栏目id",required = true)@RequestParam(value = "categoryId")long categoryId) {
		Article article = new Article();
		article.setId(id);
		article.setTitle(title);
		article.setContent(content);
		//article.setSource(source);
		//	article.setAuthorId(authorId);
		article.setCategoryId(categoryId);
		articleService.saveOrUpdate(article);
		return MessageUtil.success("更新成功");
	}
	
	@GetMapping(value = "/deleteById")
	public Message deleteById(
			@ApiParam(value ="编号",required = true)@RequestParam(value = "id") Long id) {
		articleService.deleteById(id);
		return MessageUtil.success("删除成功");
	}
}
