package com.liawan.web.controller.admin;

import com.liawan.model.domain.Options;
import com.liawan.model.dto.JsonResult;
import com.liawan.model.dto.LiawanConst;
import com.liawan.model.enums.LiawanEnums;
import com.liawan.service.OptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author :
 * @createDate : 2018年10月12日
 */

@Controller
@RequestMapping("/admin/option")
public class OptionsController extends BaseController {
	@Autowired
	private OptionsService optionsService;

	/**
	 * 所有设置选项
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping
	public String option(Model model) {
		return "admin/admin_options";
	}

	/**
	 * 保存设置
	 * 
	 * @param map
	 * @return
	 */
	@PostMapping(value = "/save")
	@ResponseBody
	public JsonResult save(@RequestParam Map<String, String> map) {
		try {
			optionsService.save(map);
			LiawanConst.OPTIONS.clear();
			List<Options> listMap = optionsService.selectMap();
			for (Options options : listMap) {
				LiawanConst.OPTIONS.put(options.getOptionName(), options.getOptionValue());
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			return new JsonResult(LiawanEnums.PRESERVE_ERROR.isFlag(), LiawanEnums.PRESERVE_ERROR.getMessage());
		}
		return new JsonResult(LiawanEnums.PRESERVE_SUCCESS.isFlag(), LiawanEnums.PRESERVE_SUCCESS.getMessage());
	}
}
