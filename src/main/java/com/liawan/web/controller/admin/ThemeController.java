package com.liawan.web.controller.admin;

import cn.hutool.core.date.DateUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.github.pagehelper.PageInfo;
import com.liawan.model.domain.Log;
import com.liawan.model.domain.Theme;
import com.liawan.model.dto.JsonResult;
import com.liawan.model.dto.LogConstant;
import com.liawan.model.dto.LiawanConst;
import com.liawan.model.enums.LiawanEnums;
import com.liawan.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author :
 * @createDate : 2019年1月3日
 */
@Controller
@RequestMapping("/admin/theme")
public class ThemeController extends BaseController {
	@Autowired
	private ThemeService themeService;

	/**
	 * 查看所有主题
	 * 
	 * @param model
	 * @param page
	 * @param limit
	 * @return
	 */
	@GetMapping
	public String theme(Model model, @RequestParam(value = "page", defaultValue = "1") int page,
                        @RequestParam(value = "limit", defaultValue = "10") int limit) {
		PageInfo<Theme> info = themeService.findPageTheme(page, limit);
		model.addAttribute("info", info);
		return "admin/admin_theme";
	}

	/**
	 * 保存主题
	 * 
	 * @param theme
	 * @param request
	 * @return
	 */
	@PostMapping("/saveTheme")
	@ResponseBody
	public JsonResult saveTheme(Theme theme, HttpServletRequest request) {
		try {
			Theme th = themeService.findByThemeName(theme.getThemeName());
			if (th != null) {
				return new JsonResult(LiawanEnums.PRESERVE_ERROR.isFlag(), "该主题已存在");
			}
			themeService.saveTheme(theme);
			// 添加日志
			logService.save(new Log(LogConstant.PUBLISH_AN_THEME, LogConstant.SUCCESS, ServletUtil.getClientIP(request),
					DateUtil.date()));
		} catch (Exception e) {
			log.error(e.getMessage());
			return new JsonResult(LiawanEnums.PRESERVE_ERROR.isFlag(), LiawanEnums.PRESERVE_ERROR.getMessage());
		}
		return new JsonResult(LiawanEnums.PRESERVE_SUCCESS.isFlag(), LiawanEnums.PRESERVE_SUCCESS.getMessage());
	}

	/**
	 * 删除主题
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@GetMapping("/remove")
	public String remove(int id, HttpServletRequest request) {
		try {
			themeService.remove(id);
			LiawanConst.THEME_NAME=null;
			// 添加日志
			logService.save(new Log(LogConstant.REMOVE_AN_THEME, LogConstant.SUCCESS, ServletUtil.getClientIP(request),
					DateUtil.date()));
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return "redirect:/admin/theme";
	}

	/**
	 * 跳转主题设置页面
	 * @param themeName
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/{themeName}")
	public String themeOption(@PathVariable String themeName, Model model) {
		Theme theme = themeService.findByThemeName(themeName);
		model.addAttribute("theme", theme);
		return "themes/" + themeName + "/module/options";
	}
	/**
	 * 启用主题
	 * @param id
	 * @return
	 */
	@PostMapping(value="themeEnabled")
	@ResponseBody
	public JsonResult themeEnabled(@RequestParam(value="id")int id) {
		try {
			themeService.themeEnabled(id);
			LiawanConst.THEME_NAME=themeService.getEnabledTheme();
		} catch (Exception e) {
			log.error(e.getMessage());
			return new JsonResult(LiawanEnums.ERROR.isFlag(),LiawanEnums.ERROR.getMessage());
		}
		return new JsonResult(LiawanEnums.OPERATION_SUCCESS.isFlag(), LiawanEnums.OPERATION_SUCCESS.getMessage());
	}
	
}
