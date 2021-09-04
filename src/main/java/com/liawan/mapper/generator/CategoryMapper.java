package com.liawan.mapper.generator;

import com.liawan.model.domain.Category;
import com.liawan.model.domain.CategoryExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryMapper {
	long countByExample(CategoryExample example);

	int deleteByExample(CategoryExample example);

	int deleteByPrimaryKey(Integer categoryId);

	int insert(Category record);

	int insertSelective(Category record);

	List<Category> selectByExample(CategoryExample example);

	Category selectByPrimaryKey(Integer categoryId);

	int updateByExampleSelective(@Param("record") Category record, @Param("example") CategoryExample example);

	int updateByExample(@Param("record") Category record, @Param("example") CategoryExample example);

	int updateByPrimaryKeySelective(Category record);

	int updateByPrimaryKey(Category record);
}