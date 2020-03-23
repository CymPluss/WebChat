package com.amayadream.webchat.controller;

import com.amayadream.webchat.utils.PropertiesUtilCym;
import com.amayadream.webchat.utils.WordDefined;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@SessionAttributes("userid")
public class SystemController {

    @RequestMapping(value = "{userid}/SysParams", method = RequestMethod.GET)
    public String  saveSysParams(@PathVariable("userid") String userid, String Notice, String Allow,
                                 String Online, String sizePage, PropertiesUtilCym proUtil,
                                 HttpServletRequest request, WordDefined wordDefined){
        String separator = File.separator;
        Map<String,String> a = new HashMap<>();
        a.put("Notice",Notice);
        a.put("Allow",Allow);
        a.put("Online",Online);
        a.put("pageSize",sizePage);
        String path = request.getServletContext().getRealPath(separator) +separator+ wordDefined.Folder + separator + userid;
        String filePath = path + separator + userid + ".properties";
        File file = new File(filePath);
        try {
            proUtil.makePropertiesFile(a,file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/system";
    }
}
