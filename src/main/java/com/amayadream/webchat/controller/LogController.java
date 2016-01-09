package com.amayadream.webchat.controller;

import com.amayadream.webchat.pojo.Log;
import com.amayadream.webchat.service.ILogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * NAME   :  WebChat/com.amayadream.webchat.controller
 * Author :  Amayadream
 * Date   :  2016.01.10 00:23
 * TODO   :
 */
@Controller
public class LogController {
    @Resource private Log log;
    @Resource private ILogService logService;

    @RequestMapping(value = "{userid}/log")
    public ModelAndView selectAll(@PathVariable("userid") String userid, @RequestParam int page){
        int pageSize = 5;
        ModelAndView view = new ModelAndView("apps/log");
        List<Log> list = logService.selectAll(page, pageSize);
        int count = logService.selectCount(pageSize);
        view.addObject("list", list);
        view.addObject("count", count);
        return view;
    }
}