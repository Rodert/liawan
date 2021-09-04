package com.liawan.mapper.custom;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author :
 * @createDate : 2018年9月27日 下午4:16:50
 */
public interface TagMapperCustom {

	List<Integer> selectByarticleId(Integer id);

	void delete(@Param(value = "list") List<Integer> tagList, @Param(value = "articleId") Integer articleId);

}
