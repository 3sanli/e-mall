package top.showtan.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import top.showtan.model.FavoritesModel;
import top.showtan.model.criteria.FavoritesCriteria;
import top.showtan.service.FavoritesService;
import top.showtan.util.AppCondition;
import top.showtan.util.PageModel;
import top.showtan.util.Pager;
import top.showtan.util.Response;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/product/favorites")
public class FavoritesController {
    @Autowired
    private FavoritesService favoritesService;

    @RequestMapping("/save")
    @ResponseBody
    public Response favorites(@RequestBody FavoritesModel favorite) {
        //TODO
        favorite.setCreatorId(1);
        favorite.setCreatorName("user01");
        FavoritesCriteria criteria = new FavoritesCriteria();
        BeanUtils.copyProperties(favorite, criteria);
        Long count = favoritesService.countFavoriets(criteria);
        if (count != 0) {
            return Response.SUCCESS();
        }
        favoritesService.save(favorite);
        return Response.SUCCESS();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Response delete(@RequestBody FavoritesModel favorite) {
        //TODO
        favorite.setCreatorId(1);
        FavoritesCriteria criteria = new FavoritesCriteria();
        BeanUtils.copyProperties(favorite, criteria);
        Long count = favoritesService.countFavoriets(criteria);
        if (count == 0) {
            return Response.SUCCESS();
        }
        favoritesService.delete(favorite);
        return Response.SUCCESS();
    }

    @RequestMapping("/search")
    @ResponseBody
    public Map<String, Object> search(@RequestParam(value = "searchInfo", required = false) String searchInfo,
                                      @RequestParam(value = "page", defaultValue = AppCondition.INIT_PAGE) Long page,
                                      @RequestParam(value = "pageSize", defaultValue = AppCondition.INIT_PAGESIZE) Long pageSize) {
        Map<String, Object> result = new HashMap<>();
        FavoritesCriteria criteria = new FavoritesCriteria();
        if (!StringUtils.isEmpty(searchInfo)) {
            criteria = JSON.parseObject(searchInfo, FavoritesCriteria.class);
        }
        PageModel<FavoritesModel> pageModel = favoritesService.search(criteria, page, pageSize);
        Pager pager = new Pager(pageModel.getTotalCount(), page, pageSize);
        result.put("favorites", pageModel.getData());
        result.put("pager", pager);
        return result;
    }
}