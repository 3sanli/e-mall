package top.showtan.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.showtan.dao.ProductCategoryMapper;
import top.showtan.entity.ProductCategoryDict;
import top.showtan.model.ProductCategoryDictModel;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductCategoryService {
    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    public List<ProductCategoryDictModel> search() {
        List<ProductCategoryDict> productCategoryDicts = productCategoryMapper.search();
        return convertProductCategoryDictListToProductCategoryDictModelList(productCategoryDicts);
    }

    private List<ProductCategoryDictModel> convertProductCategoryDictListToProductCategoryDictModelList(List<ProductCategoryDict> productCategoryDicts) {
        List<ProductCategoryDictModel> productCategoryDictModels = new ArrayList<>();
        if (!CollectionUtils.isEmpty(productCategoryDicts)) {
            for(ProductCategoryDict productCategoryDict:productCategoryDicts){
                ProductCategoryDictModel productCategoryDictModel = new ProductCategoryDictModel();
                BeanUtils.copyProperties(productCategoryDict,productCategoryDictModel);
                productCategoryDictModels.add(productCategoryDictModel);
            }
        }
        return productCategoryDictModels;
    }
}
