package top.showtan.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.showtan.model.UserModel;
import top.showtan.model.criteria.UserCriteria;
import top.showtan.service.UserService;
import top.showtan.util.Response;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    @ResponseBody
    public Response login(@RequestBody UserModel user, HttpSession session) {
        UserCriteria criteria = new UserCriteria();
        if (user != null) {
            BeanUtils.copyProperties(user, criteria);
        }
        UserModel userModel = userService.search(criteria);
        if (userModel == null) {
            return Response.ERROR("404");
        }
        if (!userModel.getPassword().equals(user.getPassword())) {
            return Response.ERROR("000");
        }
        session.setAttribute("user", userModel);
        return Response.SUCCESS();
    }

    @RequestMapping("/search")
    @ResponseBody
    public Response search(@RequestBody UserModel user) {
        UserCriteria criteria = new UserCriteria();
        if (user != null) {
            BeanUtils.copyProperties(user, criteria);
        }
        UserModel userModel = userService.search(criteria);
        if (userModel != null) {
            return Response.ERROR("000");
        }
        //为空情况
        return Response.ERROR("404");
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
