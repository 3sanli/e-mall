package top.showtan.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.showtan.model.UserModel;
import top.showtan.model.criteria.UserCriteria;
import top.showtan.service.UserPictureService;
import top.showtan.service.UserService;
import top.showtan.util.Response;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserPictureService userPictureService;

    @RequestMapping("/login")
    @ResponseBody
    public Response login(@RequestBody UserModel user, HttpSession session) {
        UserCriteria criteria = new UserCriteria();
        if (user != null) {
            BeanUtils.copyProperties(user, criteria);
        }
        UserModel userModel = userService.search(criteria);
        if (!userModel.getPassword().equals(user.getPassword())) {
            return Response.ERROR("000");
        }
        session.setAttribute("user", userModel);
        return Response.SUCCESS();
    }


    @RequestMapping("/modify")
    @ResponseBody
    public Response modify(@RequestBody UserModel user,HttpSession session){
        userService.modify(user);
        UserCriteria criteria = new UserCriteria();
        criteria.setId(user.getId());
        UserModel userModel = userService.search(criteria);
        session.setAttribute("user",userModel);
        return Response.SUCCESS();
    }

    @RequestMapping("/search")
    @ResponseBody
    public Map<String,Object> search(@RequestBody UserModel user) {
        Map<String,Object> result = new HashMap<>();
        UserCriteria criteria = new UserCriteria();
        if (user != null) {
            BeanUtils.copyProperties(user, criteria);
        }
        UserModel userModel = userService.search(criteria);
        if(userModel != null){
            userModel.setPassword(null);
            userModel.setPicture(userPictureService.getByUserId(userModel.getId()));
        }
        result.put("user",userModel);
        return result;
    }

    @RequestMapping("/register")
    @ResponseBody
    public Response register(@RequestBody UserModel user) {
        userService.save(user);
        return Response.SUCCESS();
    }

    @RequestMapping("/logout")
    @ResponseBody
    public Response logout(HttpSession session) {
        session.removeAttribute("user");
        return Response.SUCCESS();
    }
}
