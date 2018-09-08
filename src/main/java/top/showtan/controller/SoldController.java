package top.showtan.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import top.showtan.model.SoldModel;
import top.showtan.model.criteria.SoldCriteria;
import top.showtan.service.SoldService;
import top.showtan.service.UserService;
import top.showtan.util.AppCondition;
import top.showtan.util.PageModel;
import top.showtan.util.Pager;
import top.showtan.util.Response;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/product/sold")
public class SoldController {
    @Autowired
    private SoldService soldService;

    @Autowired
    private UserService userService;

    @RequestMapping("/search")
    @ResponseBody
    public Map<String, Object> search(@RequestParam(value = "searchInfo", required = false) String searchInfo,
                                      @RequestParam(value = "page", defaultValue = AppCondition.INIT_PAGE) Long page,
                                      @RequestParam(value = "pageSize", defaultValue = AppCondition.INIT_PAGESIZE) Long pageSize) {
        Map<String, Object> result = new HashMap<>();
        SoldCriteria criteria = new SoldCriteria();
        if (!StringUtils.isEmpty(searchInfo)) {
            criteria = JSON.parseObject(searchInfo, SoldCriteria.class);
        }
        criteria.setCreatorId(userService.getCurrentUser().getId());
        PageModel<SoldModel> pageModel = soldService.search(criteria, page, pageSize);
        Pager pager = new Pager(pageModel.getTotalCount(), page, pageSize);
        result.put("solds", pageModel.getData());
        result.put("pager", pager);
        return result;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Response delete(@RequestParam(value = "id",required = true)Integer id){
        //先判断数据库中是否存在本人卖出
        SoldModel sold = soldService.getById(id);
        if(sold == null){
            return Response.SUCCESS();
        }
        soldService.delete(sold);
        return Response.SUCCESS();
    }
}
