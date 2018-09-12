package top.showtan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import top.showtan.model.LogModel;
import top.showtan.model.criteria.LogCriteria;
import top.showtan.service.LogService;
import top.showtan.util.AppCondition;
import top.showtan.util.PageModel;
import top.showtan.util.Pager;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/log")
public class LogController {
    @Autowired
    private LogService logService;

    @RequestMapping("/search")
    @ResponseBody
    public Map<String, Object> search(@RequestParam(value = "id", required = true) Integer id,
                                      @RequestParam(value = "page", defaultValue = AppCondition.INIT_PAGE) Long page,
                                      @RequestParam(value = "pageSize", defaultValue = AppCondition.INIT_PAGESIZE) Long pageSize) {
        Map<String, Object> result = new HashMap<>();
        LogCriteria logCriteria = new LogCriteria();
        logCriteria.setCreatorId(id);
        PageModel<LogModel> pageModel = logService.search(logCriteria, page, pageSize);
        Pager pager = new Pager(pageModel.getTotalCount(), page, pageSize);
        result.put("logs", pageModel.getData());
        result.put("pager", pager);
        return result;
    }
}
