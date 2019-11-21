package com.briup.apps.cms.service;

import java.util.List;

import com.briup.apps.cms.bean.Article;
import com.briup.apps.cms.bean.extend.ArticleExtend;
import com.briup.apps.cms.utils.CustomerException;

/**
 *@program cms
 *@description 
 *@author ME
 */
public interface IArticleService {
	List<Article> findAll();
	
	void saveOrUpdate(Article article) throws CustomerException;
	
	//通过查找所有显示类别，作者等
	List<ArticleExtend> cascadefindAll();
	//通过id查找 然后显示评论等信息
	ArticleExtend findById(long id);
	
	void deleteById(long id)throws CustomerException;
}
