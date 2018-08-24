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
import top.showtan.model.ProductModel;
import top.showtan.model.criteria.ProductCriteria;
import top.showtan.service.ProductService;
import top.showtan.util.AppCondition;
import top.showtan.util.PageModel;
import top.showtan.util.Pager;
import top.showtan.util.Response;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;


    @RequestMapping("/search")
    @ResponseBody
    public Map<String, Object> search(@RequestParam(value = "searchInfo", required = false) String searchInfo,
                                      @RequestParam(value = "page", defaultValue = AppCondition.INIT_PAGE) Long page,
                                      @RequestParam(value = "pageSize", defaultValue = AppCondition.INIT_PAGESIZE) Long pageSize) {
        Map<String, Object> result = new HashMap<>();
        ProductCriteria criteria = new ProductCriteria();
        if (!StringUtils.isEmpty(searchInfo)) {
            criteria = JSON.parseObject(searchInfo, ProductCriteria.class);
        }
        PageModel<ProductModel> pageModel = productService.search(criteria, page, pageSize);
        Pager pager = new Pager(pageModel.getTotalCount(), page, pageSize);
        result.put("products", pageModel.getData());
        result.put("pager", pager);
        return result;
    }

    @RequestMapping("/save")
    @ResponseBody
    public Response save(@RequestBody ProductModel product) {
        ProductCriteria criteria = new ProductCriteria();
        BeanUtils.copyProperties(product, criteria);
        ProductModel productModel = productService.getById(criteria);
        if(productModel != null){
            if (!"RELEASING".equals(productModel.getStatus())){
                return Response.ERROR("该商品不可更改信息");
            }
            else{
                productService.modify(product);
                return Response.SUCCESS();
            }
        }
        //--------TODO MODIFY THE CREATORID AND CREATORNAME
        product.setCreatorId(1);
        product.setCreatorName("user01");
        productService.save(product);
        return Response.SUCCESS();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Response delete(@RequestBody ProductModel product){
        ProductCriteria criteria = new ProductCriteria();
        BeanUtils.copyProperties(product,criteria);
        ProductModel productModel = productService.getById(criteria);
        if(productModel == null){
            return Response.SUCCESS();
        }
        productService.delete(product);
        return Response.SUCCESS();
    }

}