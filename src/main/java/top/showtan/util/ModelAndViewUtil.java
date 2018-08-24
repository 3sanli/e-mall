package top.showtan.util;

import org.springframework.web.servlet.ModelAndView;

public class ModelAndViewUtil {
    public static ModelAndView CreateModelAndView(String contentView){
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("contentView",contentView);
        return mv;
    }
}
