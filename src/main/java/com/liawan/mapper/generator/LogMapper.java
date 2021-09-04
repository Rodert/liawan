package com.liawan.mapper.generator;

import com.liawan.model.domain.Log;
import com.liawan.model.domain.LogExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LogMapper {
	long countByExample(LogExample example);

	int deleteByExample(LogExample example);

	int deleteByPrimaryKey(Integer logId);

	int insert(Log record);

	int insertSelective(Log record);

	List<Log> selectByExample(LogExample example);

	Log selectByPrimaryKey(Integer logId);

	int updateByExampleSelective(@Param("record") Log record, @Param("example") LogExample example);

	int updateByExample(@Param("record") Log record, @Param("example") LogExample example);

	int updateByPrimaryKeySelective(Log record);

	int updateByPrimaryKey(Log record);
}