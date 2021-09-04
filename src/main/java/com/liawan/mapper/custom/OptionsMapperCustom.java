package com.liawan.mapper.custom;

import com.liawan.model.domain.Options;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author :
 * @createDate : 2018年10月14日
 * 
 */
public interface OptionsMapperCustom {
	/**
	 * 保存
	 * 
	 * @param map
	 */
	void saveMap(@Param("map") Map<String, Object> map);

	/**
	 * 所有设置选项
	 * 
	 * @return map
	 */
	List<Options> selectMap();

}
