package com.liawan.service.impl;

import cn.hutool.core.util.StrUtil;
import com.liawan.mapper.generator.OptionsMapper;
import com.liawan.model.domain.Options;
import com.liawan.model.domain.OptionsExample;
import com.liawan.model.domain.OptionsExample.Criteria;
import com.liawan.service.OptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author :
 * @createDate : 2018年10月12日
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class OptionsServiceImpl implements OptionsService {
	private static final String ARTICLES_CACHE_NAME = "articles";
	
	@Autowired
	private OptionsMapper optionsMapper;

	@Override
	@CacheEvict(value = ARTICLES_CACHE_NAME, allEntries = true, beforeInvocation = true)
	public void save(Map<String, String> map) throws Exception {
		if (!map.isEmpty() && null != map) {
			map.forEach((k, v) -> saveOption(k, v));
		}
	}

	@Override
	public List<Options> selectMap() {
		return optionsMapper.selectByExample(null);
	}

	@Override
	public void saveOption(String key, String value) {
		if (StrUtil.isNotEmpty(key)) {
			OptionsExample example = new OptionsExample();
			Criteria criterion = example.createCriteria();
			criterion.andOptionNameEqualTo(key);
			List<Options> list = optionsMapper.selectByExample(example);
			if (list.size() == 0 || list == null) {
				Options option = new Options();
				option.setOptionName(key);
				option.setOptionValue(value);
				optionsMapper.insert(option);
			} else {
				Options options = new Options();
				options.setOptionName(list.get(0).getOptionName());
				options.setOptionValue(value);
				optionsMapper.updateByPrimaryKeySelective(options);
			}
		}
	}

	@Override
	public void delete(Options options) throws Exception {
		optionsMapper.deleteByPrimaryKey(options.getOptionName());
	}
}
