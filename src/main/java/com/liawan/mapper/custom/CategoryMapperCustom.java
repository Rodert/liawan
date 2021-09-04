package com.liawan.mapper.custom;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * @author
 *
 */
public interface CategoryMapperCustom {

	List<Integer> selectByarticleId(Integer id);

	void delete(@Param(value = "list") List<Integer> cateList, @Param(value = "articleId") Integer articleId);

}