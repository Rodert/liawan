package com.liawan.service;

import com.github.pagehelper.PageInfo;
import com.liawan.model.domain.Attachment;

import java.util.List;

/**
 * @author :
 * @createDate : 2018年9月7日
 */
public interface AttachmentService {
	/**
	 * 保存
	 * 
	 * @param attachment
	 */
	void save(Attachment attachment) throws Exception;

	/**
	 * 分页查询附件
	 * 
	 * @param page
	 * @param limit
	 * @return
	 */
	PageInfo<Attachment> getAttachment(int page, int limit);

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	Attachment findById(int id);

	/**
	 * 删除
	 * 
	 * @param id
	 */
	void deleteAttachment(int id) throws Exception;

	/**
	 * 所有附件
	 * 
	 * @return
	 */
	List<Attachment> countAttachment();

}
