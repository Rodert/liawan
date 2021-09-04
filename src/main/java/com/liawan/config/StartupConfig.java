package com.liawan.config;

import com.liawan.model.domain.Options;
import com.liawan.model.dto.LiawanConst;
import com.liawan.service.MenuService;
import com.liawan.service.OptionsService;
import com.liawan.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.List;

/**
 * @author :
 * @createDate : 2018年10月30日
 */
@Configuration
public class StartupConfig implements ApplicationListener<ContextRefreshedEvent> {
	@Autowired
	private OptionsService optionsService;
	@Autowired
	private MenuService menuService;
	@Autowired
	private ThemeService themeService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		this.loadOptions();
		this.loadMenus();
		this.loadThemeName();
	}

	/**
	 * 加载设置选项
	 */
	private void loadOptions() {
		List<Options> listMap = optionsService.selectMap();
		if (listMap.size() > 0 && !listMap.isEmpty()) {
			for (Options options : listMap) {
				LiawanConst.OPTIONS.put(options.getOptionName(), options.getOptionValue());
			}
		}
	}

	/**
	 * 加载菜单
	 */
	private void loadMenus() {
		LiawanConst.MENUS = menuService.findMenus();
	}
	/**
	 * 加载主题
	 */
	private void loadThemeName() {
		LiawanConst.THEME_NAME=themeService.getEnabledTheme();
	}

}
