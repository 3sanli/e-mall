package top.showtan.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import top.showtan.model.*;
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

    @Autowired
    private UserService userService;

    @Autowired
    private LogService logService;


    /*@RequestMapping({"/", "/index"})
    public ModelAndView index(@RequestParam(value = "searchInfo", required = false) String searchInfo,
                                    @RequestParam(value = "page", defaultValue = AppCondition.INIT_PAGE) Long page,
                                    @RequestParam(value = "pageSize", defaultValue = AppCondition.INIT_PAGESIZE) Long pageSize) {
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
    }*/

    @RequestMapping({"/portal/product/list", "/"})
    public ModelAndView listProduct(@RequestParam(value = "searchInfo", required = false) String searchInfo,
                                    @RequestParam(value = "page", defaultValue = AppCondition.INIT_PAGE) Long page,
                                    @RequestParam(value = "pageSize", defaultValue = AppCondition.INIT_PAGESIZE) Long pageSize) {

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
        criteria.setCreatorId(userService.getCurrentUser().getId());
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
        favoritesCriteria.setCreatorId(userService.getCurrentUser().getId());
        mv.addObject("product", productService.getById(criteria));
        mv.addObject("isFavorites", (favoritesService.countFavoriets(favoritesCriteria) == 0) ? false : true);
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
        criteria.setCreatorId(userService.getCurrentUser().getId());
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
        criteria.setCreatorId(userService.getCurrentUser().getId());
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
        criteria.setCreatorId(userService.getCurrentUser().getId());
        PageModel<SoldModel> pageModel = soldService.search(criteria, page, pageSize);
        Pager pager = new Pager(pageModel.getTotalCount(), page, pageSize);
        mv.addObject("solds", pageModel.getData());
        mv.addObject("pager", pager);
        return mv;
    }

    @RequestMapping("/product/comment")
    @ResponseBody
    public ModelAndView comment(@RequestParam(value = "productId", required = true) Integer productId,
                                @RequestParam(value = "model", required = true) Integer model) {
        ModelAndView mv = ModelAndViewUtil.CreateModelAndView("views/productComment");
        mv.addObject("productId", productId);
        mv.addObject("model", model);
        return mv;
    }

    @RequestMapping("/portal/login")
    @ResponseBody
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @RequestMapping("/portal/register")
    @ResponseBody
    public ModelAndView register() {
        return new ModelAndView("register");
    }

    @RequestMapping("/portal/personal")
    @ResponseBody
    public ModelAndView personal(@RequestParam(value = "id", required = true) Integer id) {
        ModelAndView mv = ModelAndViewUtil.CreateModelAndView("views/personal");
        mv.addObject("id", id);
        return mv;
    }

    @RequestMapping("/portal/personal/user/info")
    @ResponseBody
    public ModelAndView userInfo(@RequestParam(value = "id", required = true) Integer id) {
        ModelAndView mv = new ModelAndView("views/userInfo");
        UserCriteria criteria = new UserCriteria();
        criteria.setId(id);
        mv.addObject("user", userService.search(criteria));
        return mv;
    }

    @RequestMapping("/portal/personal/user/info/modify")
    @ResponseBody
    public ModelAndView userInfoModify() {
        ModelAndView mv = new ModelAndView("views/myInfoModify");
        mv.addObject("user", userService.getCurrentUser());
        return mv;
    }

    @RequestMapping("/portal/personal/user/recent")
    @ResponseBody
    public ModelAndView userRecent(@RequestParam(value = "id", required = true) Integer id,
                                   @RequestParam(value = "page", defaultValue = AppCondition.INIT_PAGE) Long page,
                                   @RequestParam(value = "pageSize", defaultValue = AppCondition.INIT_PAGESIZE) Long pageSize) {
        ModelAndView mv = new ModelAndView("views/userRecent");
        UserCriteria userCriteria = new UserCriteria();
        userCriteria.setId(id);
        UserModel user = userService.search(userCriteria);
        LogCriteria logCriteria = new LogCriteria();
        logCriteria.setCreatorId(user.getId());
        PageModel<LogModel> pageModel = logService.search(logCriteria, page, pageSize);
        Pager pager = new Pager(pageModel.getTotalCount(), page, pageSize);
        mv.addObject("logs", pageModel.getData());
        mv.addObject("pager", pager);
        return mv;
    }

}
