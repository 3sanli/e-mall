package top.showtan.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.showtan.dao.ProductMapper;
import top.showtan.dao.ProductPictureMapper;
import top.showtan.entity.Product;
import top.showtan.entity.ProductPicture;
import top.showtan.model.ProductModel;
import top.showtan.model.criteria.ProductCriteria;
import top.showtan.util.BaseConvert;
import top.showtan.util.PageModel;
import top.showtan.util.PageUtil;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductPictureMapper productPictureMapper;

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    /**
     * 根据条件分页查询商品结果集
     *
     * @param criteria
     * @param page
     * @param pageSize
     * @return
     */
    public PageModel<ProductModel> search(ProductCriteria criteria, Long page, Long pageSize) {
        PageModel<ProductModel> result = new PageModel<>();
        PageUtil pageUtil = new PageUtil(page, pageSize);
        List<Product> products = productMapper.search(criteria, pageUtil.getSkip(), pageUtil.getTake());
        Long totalCount = productMapper.countAll(criteria);

        List<ProductModel> productModels = BaseConvert.convertProductListToProductModelList(products);
        result.setData(productModels);
        result.setTotalCount(totalCount);

        List<ProductCriteria> criterias = BaseConvert.convertProductModelListToProductCriteriaList(result.getData());
        List<ProductPicture> pictures = productPictureMapper.getByProductIdList(criterias);

        BaseConvert.mapProductAndPicture(result.getData(), pictures);
        return result;
    }

    /**
     * 用户发布商品，持久化商品到数据库中
     *
     * @param product
     * @return
     */
    public void save(ProductModel product) {
        sqlSessionTemplate.insert("top.showtan.dao.ProductMapper.save", product);
        productPictureMapper.save(createProductPictureList(product));
    }

    /**
     * 将商品图片从商品中单独拿出,用以保存图片
     *
     * @param productModel
     * @return
     */
    private List<ProductPicture> createProductPictureList(ProductModel productModel) {
        List<ProductPicture> productPictures = new ArrayList<>();
        if (!CollectionUtils.isEmpty(productModel.getPictures())) {
            for (ProductPicture picture : productModel.getPictures()) {
                ProductPicture productPicture = new ProductPicture();
                BeanUtils.copyProperties(picture, productPicture);
                productPicture.setProductId(productModel.getId());
                productPictures.add(productPicture);
            }
        }
        return productPictures;
    }

    /**
     * 更新商品状态
     *
     * @param productId
     * @param status
     */
    public void updateStatus(Integer productId, String status) {
        productMapper.updateStatus(productId, status);
    }

    /**
     * 更新商品信息
     *
     * @param productModel
     */
    public void modify(ProductModel productModel) {
        productMapper.modify(productModel);
        productPictureMapper.deleteByProductId(productModel.getId());
        productPictureMapper.save(createProductPictureList(productModel));
    }

    /**
     * 根据商品id查询商品详情
     *
     * @param criteria
     * @return
     */
    public ProductModel getById(ProductCriteria criteria) {
        if (criteria.getId() == null) {
            return null;
        }
        Product product = productMapper.getById(criteria);
        List<ProductPicture> productPictures = productPictureMapper.getByProductId(criteria.getId());
        ProductModel productModel = new ProductModel();
        BeanUtils.copyProperties(product, productModel);
        productModel.setPictures(productPictures);
        return productModel;
    }

    /**
     * 删除指定id商品和其下所有图片
     *
     * @param product
     */
    public void delete(ProductModel product) {
        productPictureMapper.deleteByProductId(product.getId());
        productMapper.deleteById(product.getId());
    }
}
