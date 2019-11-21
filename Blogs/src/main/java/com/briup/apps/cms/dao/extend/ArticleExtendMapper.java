package com.briup.apps.cms.dao.extend;

import java.util.List;

import com.briup.apps.cms.bean.extend.ArticleExtend;

/**
 *@program cms
 *@description 
 *@author ME
 */
public interface ArticleExtendMapper{
	
	List<ArticleExtend>	selectAll();
	
	ArticleExtend selectById(long id);
}
