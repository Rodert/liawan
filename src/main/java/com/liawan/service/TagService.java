package com.liawan.service;

import com.liawan.model.domain.Tag;

import java.util.List;

/**
 * @author :
 * @createDate : 2018年9月25日
 * 
 */
public interface TagService {
	/**
	 * 查询所有标签
	 * 
	 * @return
	 */
	List<Tag> findTags();

	/**
	 * 根据tagid查询
	 * 
	 * @param tagId
	 * @return
	 */
	Tag findByTagId(int tagId);

	/**
	 * 保存
	 * 
	 * @param tag
	 */
	void save(Tag tag) throws Exception;

	/**
	 * 修改
	 * 
	 * @param tag
	 */
	void update(Tag tag) throws Exception;

	/**
	 * 删除
	 * 
	 * @param tagId
	 */
	void remove(int tagId) throws Exception;

	/**
	 * url查询标签是否存在
	 * 
	 * @param tagUrl
	 * @return
	 */
	Tag findByTagUrl(String tagUrl);

}
