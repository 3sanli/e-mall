package top.showtan.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import top.showtan.model.BuyModel;
import top.showtan.model.ProductModel;
import top.showtan.model.SoldModel;
import top.showtan.model.criteria.BuyCriteria;
import top.showtan.model.criteria.ProductCriteria;
import top.showtan.service.BuyService;
import top.showtan.service.ProductService;
import top.showtan.service.SoldService;
import top.showtan.service.UserService;
import top.showtan.util.*;


@Controller
@RequestMapping("/product/swap")
public class BuyController {
    @Autowired
    private BuyService buyService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private SoldService soldService;

    @RequestMapping("/save")
    @ResponseBody
    public Response save(@RequestBody BuyModel buy) {
        //--------TODO MODIFY THE CREATORID AND CREATORNAME
        buy.setCreatorId(1);
        buy.setCreatorName("user01");

        //先判断该商品有没有在同一时间被人购买
        ProductCriteria productCriteria = new ProductCriteria();
        productCriteria.setId(buy.getProductId());
        ProductModel productModel = productService.getById(productCriteria);
        if (!ProductStatus.RELEASING.equals(productModel.getStatus())) {
            return Response.ERROR("商品已被抢购");
        }
        //其次判断该商品本人有没有已经购买
        BuyCriteria buyCriteria = new BuyCriteria();
        BeanUtils.copyProperties(buy, buyCriteria);
        Long count = buyService.countBuy(buyCriteria);
        if (count != 0) {
            return Response.SUCCESS();
        }

        if (!userService.costMoney(buy)) {
            return Response.ERROR("您的账户余额不足");
        }
        buyService.save(buy);
        productService.updateStatus(buy.getProductId(), ProductStatus.SWAPING);

        return Response.SUCCESS();
    }

    @RequestMapping("/cancel")
    @ResponseBody
    public Response cancelBuy(@RequestParam(value = "id",required = true) Integer id) {
        //先判断数据库中是否存在本人购买记录
        BuyModel buy = buyService.getById(id);
        if(buy == null){
            return Response.SUCCESS();
        }

        ProductCriteria productCriteria = new ProductCriteria();
        productCriteria.setId(buy.getProductId());
        ProductModel productModel = productService.getById(productCriteria);
        userService.addMoney(buy.getCreatorId(), productModel.getPrice());

        buyService.cancelBuy(buy);
        productService.updateStatus(buy.getProductId(), ProductStatus.RELEASING);
        return Response.SUCCESS();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Response deleteBuy(@RequestParam(value = "id",required = true) Integer id) {
        //先判断数据库中是否存在本人购买记录
        BuyModel buy = buyService.getById(id);
        if(buy == null){
            return Response.SUCCESS();
        }
        buyService.delete(buy);
        return Response.SUCCESS();
    }

    @RequestMapping("/complete")
    @ResponseBody
    public Response complete(@RequestParam(value = "id",required = true) Integer id) {
        //先判断数据库中是否存在本人购买记录
        BuyModel buy = buyService.getById(id);
        if(buy == null){
            return Response.ERROR();
        }
        //完成购买，改变购买记录状态为"COMPLETE"
        buyService.complete(buy);
        //更新商品状态为已售出("SOLD")
        productService.updateStatus(buy.getProductId(), ProductStatus.SOLD);

        //完成钱财交易支付
        ProductCriteria productCriteria = new ProductCriteria();
        productCriteria.setId(buy.getProductId());
        ProductModel productModel = productService.getById(productCriteria);
        userService.addMoney(productModel.getCreatorId(), productModel.getPrice());

        //将此次交易记录到卖出记录中
        SoldModel sold = new SoldModel();
        BeanUtils.copyProperties(buy, sold);
        sold.setCreatorId(productModel.getCreatorId());
        sold.setCreatorName(productModel.getCreatorName());
        soldService.save(sold);

        return Response.SUCCESS();
    }
}