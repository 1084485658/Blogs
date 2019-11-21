package com.briup.apps.cms.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.briup.apps.cms.bean.Article;
import com.briup.apps.cms.bean.ArticleExample;
import com.briup.apps.cms.bean.extend.ArticleExtend;
import com.briup.apps.cms.dao.ArticleMapper;
import com.briup.apps.cms.dao.extend.ArticleExtendMapper;
import com.briup.apps.cms.service.IArticleService;
import com.briup.apps.cms.utils.CustomerException;

/**
 *@program cms
 *@description 
 *@author ME
 */
@Service
public class AtricleServiceImpl implements IArticleService{
	@Resource
	private ArticleMapper articleMapper;
	@Resource
	private ArticleExtendMapper articleExtend;
	
	@Override
	public List<Article> findAll() {
		ArticleExample example =new ArticleExample();
		return articleMapper.selectByExample(example);
	}

	@Override
	public void saveOrUpdate(Article article) throws CustomerException{
		if(article.getId()!=null) {
			articleMapper.updateByPrimaryKey(article);
		}else {
			ArticleExample example =new ArticleExample();
			example.createCriteria().andTitleEqualTo(article.getTitle());
			List<Article> list = articleMapper.selectByExample(example);
			if(list.size()>0) {
				throw new CustomerException("文章标题不能重复");
			}
			
			//初始化
			article.setStatus(ArticleExtend.STATUS_UNCHECK);
			article.setThumpUp(0l);
			article.setThumpDown(0l);
			article.setPublishTime(new Date().getTime());
			article.setReadTimes(0l);
			articleMapper.insert(article);
		}
	}

	@Override
	public List<ArticleExtend> cascadefindAll() {
		return articleExtend.selectAll();
	}

	@Override
	public ArticleExtend findById(long id) {
		return articleExtend.selectById(id);
	}

	@Override
	public void deleteById(long id) throws CustomerException {
		Article a = articleMapper.selectByPrimaryKey(id);
		if(a!=null) {
			articleMapper.deleteByPrimaryKey(id);
		}else {
			throw new CustomerException("用户不存在");
		}
		
	}
}
