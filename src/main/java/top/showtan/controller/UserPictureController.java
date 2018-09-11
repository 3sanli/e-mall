package top.showtan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import top.showtan.service.UserPictureService;

@Controller
public class UserPictureController {
    @Autowired
    private UserPictureService userPictureService;

    @RequestMapping("search")
    @ResponseBody
    public String search(@RequestParam(value = "userId",required = true)Integer userId){
        return userPictureService.getByUserId(userId);
    }
}
