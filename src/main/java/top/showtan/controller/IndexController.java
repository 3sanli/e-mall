package top.showtan.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import top.showtan.model.BuyModel;
import top.showtan.model.FavoritesModel;
import top.showtan.model.ProductModel;
import top.showtan.model.SoldModel;
import top.showtan.model.criteria.*;
import top.showtan.service.*;
import top.showtan.util.AppCondition;
import top.showtan.util.ModelAndViewUtil;
import top.showtan.util.PageModel;
import top.showtan.util.Pager;


@Controller
public class IndexController {
    @Autowired
    private ProductService productService;

    @Autowired
    private FavoritesService favoritesService;

    @Autowired
    private BuyService buyService;

    @Autowired
    private SoldService soldService;


    @RequestMapping({"/", "/index"})
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("layouts/header");
        return mv;
    }

    @RequestMapping("/portal/product/list")
    public ModelAndView listProduct(@RequestParam(value = "searchInfo", required = false) String searchInfo,
                                    @RequestParam(value = "page", defaultValue = AppCondition.INIT_PAGE) Long page,
                                    @RequestParam(value = "pageSize", defaultValue = AppCondition.INIT_PAGESIZE) Long pageSize)  {

        ModelAndView mv = ModelAndViewUtil.CreateModelAndView("views/productList");
        ProductCriteria criteria = new ProductCriteria();
        if (!StringUtils.isEmpty(searchInfo)) {
            criteria = JSON.parseObject(searchInfo, ProductCriteria.class);
        }
        PageModel<ProductModel> pageModel = productService.search(criteria, page, pageSize);
        Pager pager = new Pager(pageModel.getTotalCount(), page, pageSize);
        mv.addObject("products", pageModel.getData());
        mv.addObject("name", criteria.getName());
        mv.addObject("pager", pager);
        return mv;
    }

    @RequestMapping("/product/favorites/list")
    public ModelAndView listFavorites(@RequestParam(value = "page", defaultValue = AppCondition.INIT_PAGE) Long page,
                                      @RequestParam(value = "pageSize", defaultValue = AppCondition.INIT_PAGESIZE) Long pageSize) {
        ModelAndView mv = ModelAndViewUtil.CreateModelAndView("views/myFavorites");
        FavoritesCriteria criteria = new FavoritesCriteria();
        //TODO
        criteria.setCreatorId(1);
        PageModel<FavoritesModel> pageModel = favoritesService.search(criteria, page, pageSize);
        Pager pager = new Pager(pageModel.getTotalCount(), page, pageSize);
        mv.addObject("favorites", pageModel.getData());
        mv.addObject("pager", pager);
        return mv;
    }

    @RequestMapping("/product/detail")
    @ResponseBody
    public ModelAndView productDetail(@RequestParam(value = "id", required = true) Integer id) {
        ModelAndView mv = ModelAndViewUtil.CreateModelAndView("views/productDetail");
        ProductCriteria criteria = new ProductCriteria();
        criteria.setId(id);
        FavoritesCriteria favoritesCriteria = new FavoritesCriteria();
        favoritesCriteria.setProductId(id);
        //TODO
        favoritesCriteria.setCreatorId(1);
        mv.addObject("product", productService.getById(criteria));
        mv.addObject("isFavorites",(favoritesService.countFavoriets(favoritesCriteria)==0)?false:true);
        return mv;
    }

    @RequestMapping("/product/release")
    @ResponseBody
    public ModelAndView release(@RequestParam(value = "id", required = false) Integer id) {
        ModelAndView mv = ModelAndViewUtil.CreateModelAndView("views/productAdd");
        ProductCriteria criteria = new ProductCriteria();
        criteria.setId(id);
        ProductModel product = productService.getById(criteria);
        mv.addObject("product", product);
        return mv;
    }

    @RequestMapping("/product/release/list")
    @ResponseBody
    public ModelAndView releaseList(@RequestParam(value = "page", defaultValue = AppCondition.INIT_PAGE) Long page,
                                    @RequestParam(value = "pageSize", defaultValue = AppCondition.INIT_PAGESIZE) Long pageSize) {
        ModelAndView mv = ModelAndViewUtil.CreateModelAndView("views/releaseProductList");
        ProductCriteria criteria = new ProductCriteria();
        //TODO
        criteria.setCreatorId(1);
        PageModel<ProductModel> pageModel = productService.search(criteria, page, pageSize);
        Pager pager = new Pager(pageModel.getTotalCount(), page, pageSize);
        mv.addObject("products", pageModel.getData());
        mv.addObject("pager", pager);
        return mv;
    }

    @RequestMapping("/product/buy/list")
    @ResponseBody
    public ModelAndView myBuyList(@RequestParam(value = "page", defaultValue = AppCondition.INIT_PAGE) Long page,
                                  @RequestParam(value = "pageSize", defaultValue = AppCondition.INIT_PAGESIZE) Long pageSize) {
        ModelAndView mv = ModelAndViewUtil.CreateModelAndView("views/myBuy");
        BuyCriteria criteria = new BuyCriteria();
        //TODO
        criteria.setCreatorId(1);
        PageModel<BuyModel> pageModel = buyService.search(criteria, page, pageSize);
        Pager pager = new Pager(pageModel.getTotalCount(), page, pageSize);
        mv.addObject("buys", pageModel.getData());
        mv.addObject("pager", pager);
        return mv;
    }

    @RequestMapping("/product/sold/list")
    @ResponseBody
    public ModelAndView mySoldList(@RequestParam(value = "page", defaultValue = AppCondition.INIT_PAGE) Long page,
                                   @RequestParam(value = "pageSize", defaultValue = AppCondition.INIT_PAGESIZE) Long pageSize) {
        ModelAndView mv = ModelAndViewUtil.CreateModelAndView("views/mySold");
        SoldCriteria criteria = new SoldCriteria();
        //TODO
        criteria.setCreatorId(1);
        PageModel<SoldModel> pageModel = soldService.search(criteria, page, pageSize);
        Pager pager = new Pager(pageModel.getTotalCount(), page, pageSize);
        mv.addObject("solds", pageModel.getData());
        mv.addObject("pager", pager);
        return mv;
    }

    @RequestMapping("/product/comment")
    @ResponseBody
    public ModelAndView comment(@RequestParam(value = "productId",required = true)Integer productId){
        ModelAndView mv = ModelAndViewUtil.CreateModelAndView("views/productComment");
        mv.addObject("productId",productId);
        return mv;
    }

    @RequestMapping("/portal/login")
    @ResponseBody
    public ModelAndView login() {
        return ModelAndViewUtil.CreateModelAndView("views/login");
    }
}
