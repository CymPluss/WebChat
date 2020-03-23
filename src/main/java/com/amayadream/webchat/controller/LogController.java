package com.amayadream.webchat.controller;

import com.amayadream.webchat.pojo.Log;
import com.amayadream.webchat.service.ILogService;
import com.amayadream.webchat.utils.PropertiesUtilCym;
import com.amayadream.webchat.utils.WordDefined;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * Author :  Amayadream
 * Date   :  2016.01.10 00:23
 * TODO   :
 */
@Controller
@RequestMapping(value = "")
public class LogController {

    @Resource
    private ILogService logService;

    @RequestMapping(value = "{userid}/log")
    public ModelAndView selectAll(@PathVariable("userid") String userid, @RequestParam(defaultValue = "1") int page,
                                  PropertiesUtilCym proUtile, WordDefined wordDefined,
                                  HttpServletRequest request) throws IOException {
        String separator = File.separator;
        int pageSize;
        try {
            Properties cc = proUtile.getProperties(new File(request.getServletContext().getRealPath(separator) +separator+
                    wordDefined.Folder + separator + userid + separator + userid + ".properties"));
             pageSize = Integer.parseInt(cc.getProperty("pageSize"));
        }catch (Exception e){
            pageSize = 10;
        }
        ModelAndView view = new ModelAndView("log");
        List<Log> list = logService.selectLogByUserid(userid, page, pageSize);
        int count = logService.selectCountByUserid(userid, pageSize);
        view.addObject("list", list);
        view.addObject("count", count);
        return view;
    }

}
