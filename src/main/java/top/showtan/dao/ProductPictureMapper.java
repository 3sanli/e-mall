package top.showtan.dao;

import org.springframework.stereotype.Repository;
import top.showtan.entity.ProductPicture;
import top.showtan.model.criteria.ProductCriteria;

import java.util.List;

@Repository
public interface ProductPictureMapper {
    /**
     * 保存商品图片（集）
     *
     * @param productPictures
     */
    void save(List<ProductPicture> productPictures);

    /**
     * 根据商品id集查询商品的图片集
     *
     * @param criterias
     * @return
     */
    List<ProductPicture> getByProductIdList(List<ProductCriteria> criterias);

    /**
     * 根据商品id查询商品的图片集
     *
     * @param productId
     * @return
     */
    List<ProductPicture> getByProductId(Integer productId);

    /**
     * 每次更新商品图片前删除原有商品图片
     *
     * @param productId
     */
    void deleteByProductId(Integer productId);
}
