package top.showtan.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.showtan.entity.Product;
import top.showtan.model.ProductModel;
import top.showtan.model.criteria.ProductCriteria;

import java.util.List;

@Repository
public interface ProductMapper {
    /**
     * 根据查询条件动态查询商品
     *
     * @param criteria
     * @param skip
     * @param take
     * @return
     */
    List<Product> search(@Param("criteria") ProductCriteria criteria,
                         @Param("skip") Long skip,
                         @Param("take") Long take);

    /**
     * 保存商品信息及返回商品id
     *
     * @param product
     * @return
     */
    Integer save(ProductModel product);

    /**
     * 查询总记录数
     *
     * @return
     */
    Long countAll(ProductCriteria criteria);


    /**
     * 用户操作触发系统更改商品状态
     *
     * @param productId
     * @param status
     */
    void updateStatus(@Param("productId") Integer productId,
                      @Param("status") String status);

    /**
     * 更新商品信息
     *
     * @param productModel
     */
    void modify(ProductModel productModel);

    /**
     * 根据商品id集合查询相关商品
     *
     * @param products
     * @return
     */
    List<Product> getByProductIdList(List<ProductCriteria> products);

    /**
     * 根据商品id查询商品详情
     *
     * @param criteria
     * @return
     */
    Product getById(ProductCriteria criteria);

    /**
     * 删除指定id商品
     *
     * @param id
     */
    void deleteById(Integer id);
}
