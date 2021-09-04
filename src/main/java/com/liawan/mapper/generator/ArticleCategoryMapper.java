package com.liawan.mapper.generator;

import com.liawan.model.domain.ArticleCategory;
import com.liawan.model.domain.ArticleCategoryExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleCategoryMapper {
	long countByExample(ArticleCategoryExample example);

	int deleteByExample(ArticleCategoryExample example);

	int insert(ArticleCategory record);

	int insertSelective(ArticleCategory record);

	List<ArticleCategory> selectByExample(ArticleCategoryExample example);

	int updateByExampleSelective(@Param("record") ArticleCategory record,
                                 @Param("example") ArticleCategoryExample example);

	int updateByExample(@Param("record") ArticleCategory record, @Param("example") ArticleCategoryExample example);
}