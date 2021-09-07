package com.liawan.web.controller.test;

import com.liawan.model.dto.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RequestMapping(value = "/test/top")
@Controller
public class Top {

    @RequestMapping(value = "/top")
    public String upload(Model model, HttpServletRequest request) {
        return "test/top";
    }
}
