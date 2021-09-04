package com.liawan.web.controller.admin;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.github.pagehelper.PageInfo;
import com.liawan.model.domain.Article;
import com.liawan.model.domain.ArticleCustom;
import com.liawan.model.domain.Log;
import com.liawan.model.domain.User;
import com.liawan.model.dto.JsonResult;
import com.liawan.model.dto.LogConstant;
import com.liawan.model.dto.LiawanConst;
import com.liawan.model.enums.LiawanEnums;
import com.liawan.model.enums.PostType;
import com.liawan.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author :
 * @createDate : 2018年12月24日
 */
@Controller
@RequestMapping("/admin/page")
public class PageController extends BaseController {
	@Autowired
	private ArticleService articleService;

	/**
	 * 显示所有页面
	 * 
	 * @param model
	 * @param page
	 * @param limit
	 * @return
	 */
	@GetMapping
	public String page(Model model, @RequestParam(value = "page", defaultValue = "1") int page,
                       @RequestParam(value = "limit", defaultValue = "10") int limit,
                       @RequestParam(value = "status", defaultValue = "0") int status) {
		ArticleCustom articleCustom = new ArticleCustom();
		articleCustom.setArticleStatus(status);
		articleCustom.setArticlePost(PostType.POST_TYPE_PAGE.getValue());
		PageInfo<ArticleCustom> pageInfo = articleService.findPageArticle(page, limit, articleCustom);
		model.addAttribute("info", pageInfo);
		// 已发布条数
		model.addAttribute("published", articleService.countByStatus(0, PostType.POST_TYPE_PAGE.getValue()));
		// 草稿条数
		model.addAttribute("draft", articleService.countByStatus(1, PostType.POST_TYPE_PAGE.getValue()));
		model.addAttribute("status", status);
		return "admin/admin_page";
	}

	/**
	 * 新建页面
	 * 
	 * @return
	 */
	@GetMapping(value = "/new")
	public String newPage() {
		return "admin/admin_new_page";
	}

	/**
	 * 保存页面
	 * 
	 * @param article
	 * @param tags
	 * @param categorys
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/new/save")
	@ResponseBody
	public JsonResult save(Article article, HttpServletRequest request) {
		try {
			if (StrUtil.isEmpty(article.getArticleTitle())) {
				return new JsonResult(false, "标题不能为空");
			}
			if (article.getId() == null) {
				// 判断文章链接是否重复
				if (!StrUtil.isEmpty(article.getArticleUrl())) {
					// 查询url是否重复
					int repeat = articleService.findRepeatByUrl(article.getArticleUrl());
					if (repeat != 0) {
						return new JsonResult(false, "路径已存在");
					}
				}
				User user = (User) request.getSession().getAttribute(LiawanConst.USER_SESSION_KEY);
				article.setUserId(user.getUserId());
				article.setArticleNewstime(DateUtil.date());
				article.setArticleUpdatetime(DateUtil.date());
				// 如果自定义链接为空则按时间戳生成链接
				if (StrUtil.isEmpty(article.getArticleUrl())) {
					article.setArticleUrl(String.valueOf(System.currentTimeMillis() / 1000));
				}
				// 如果没有选择略缩图则随机一张图
				if (StrUtil.isEmpty(article.getArticleThumbnail())) {
					article.setArticleThumbnail("/static/img/rand/" + RandomUtil.randomInt(1, 19) + ".jpg");
				}
				articleService.save(article, null, null);
				// 添加日志
				logService.save(new Log(LogConstant.PUBLISH_AN_PAGE, LogConstant.SUCCESS,
						ServletUtil.getClientIP(request), DateUtil.date()));
			} else {
				// 如果没有选择略缩图则随机一张图
				if (StrUtil.isEmpty(article.getArticleThumbnail())) {
					article.setArticleThumbnail("/static/img/rand/" + RandomUtil.randomInt(1, 19) + ".jpg");
				}
				// 页面最后修改时间
				article.setArticleUpdatetime(DateUtil.date());
				articleService.update(article, null, null);
				// 添加日志
				logService.save(new Log(LogConstant.UPDATE_AN_PAGE, LogConstant.SUCCESS,
						ServletUtil.getClientIP(request), DateUtil.date()));
			}
		} catch (Exception e) {
			log.error("添加或更新失败" + e.getMessage());
			return new JsonResult(LiawanEnums.ERROR.isFlag(), LiawanEnums.ERROR.getMessage());
		}
		return new JsonResult(LiawanEnums.PRESERVE_SUCCESS.isFlag(), LiawanEnums.PRESERVE_SUCCESS.getMessage());
	}

	/**
	 * 彻底删除页面
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/remove")
	public String remove(@RequestParam(value = "id") int id, HttpServletRequest request) {
		try {
			articleService.remove(id);
			// 添加日志
			logService.save(new Log(LogConstant.REMOVE_AN_PAGE, LogConstant.SUCCESS, ServletUtil.getClientIP(request),
					DateUtil.date()));
		} catch (Exception e) {
			log.error("删除文章失败" + e.getMessage());
		}
		return "redirect:/admin/page?status=0";
	}

	/**
	 * 修改页面
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/edit")
	public String editArticle(Model model, @RequestParam(value = "article_id") Integer article_id) {
		try {
			// 获取文章信息
			ArticleCustom articleCustom = articleService.findByArticleId(article_id);
			model.addAttribute("articleCustom", articleCustom);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return "admin/admin_edit_page";
	}
}
