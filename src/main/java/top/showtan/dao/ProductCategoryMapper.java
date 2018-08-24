package top.showtan.dao;

import org.springframework.stereotype.Repository;
import top.showtan.entity.ProductCategoryDict;

import java.util.List;

@Repository
public interface ProductCategoryMapper {
    /**
     * 查询所有商品分类信息
     *
     * @return
     */
    List<ProductCategoryDict> search();
}
