package top.showtan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.showtan.model.ProductCategoryDictModel;
import top.showtan.service.ProductCategoryService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/product/category")
public class ProductCategoryController {
    @Autowired
    private ProductCategoryService productCategoryService;

    @RequestMapping("/search")
    @ResponseBody
    public Map<String,Object> search(){
        Map<String,Object> result = new HashMap<>();
        List<ProductCategoryDictModel> productCategorys = productCategoryService.search();
        result.put("productCategorys",productCategorys);
        return result;
    }
}
