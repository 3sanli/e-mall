package top.showtan.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.showtan.dao.ProductMapper;
import top.showtan.dao.ProductPictureMapper;
import top.showtan.dao.SoldMapper;
import top.showtan.entity.Product;
import top.showtan.entity.ProductPicture;
import top.showtan.entity.Sold;
import top.showtan.model.ProductModel;
import top.showtan.model.SoldModel;
import top.showtan.model.criteria.ProductCriteria;
import top.showtan.model.criteria.SoldCriteria;
import top.showtan.util.BaseConvert;
import top.showtan.util.PageModel;
import top.showtan.util.PageUtil;

import java.util.ArrayList;
import java.util.List;

@Service
public class SoldService {
    @Autowired
    private SoldMapper soldMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductPictureMapper productPictureMapper;

    /**
     * 根据id查询指定卖出记录
     *
     * @param id
     * @return
     */
    public SoldModel getById(Integer id) {
        Sold sold = soldMapper.getById(id);
        if(sold == null){
            return null;
        }
        SoldModel soldModel = new SoldModel();
        BeanUtils.copyProperties(sold,soldModel);
        return soldModel;
    }

    /**
     * 根据条件查询收藏
     *
     * @param criteria
     * @param page
     * @param pageSize
     * @return
     */
    public PageModel<SoldModel> search(SoldCriteria criteria, Long page, Long pageSize) {
        PageModel<SoldModel> result = new PageModel<>();
        PageUtil pageUtil = new PageUtil(page,pageSize);
        List<Sold> solds = soldMapper.search(criteria, pageUtil.getSkip(), pageUtil.getTake());
        Long totalCount = soldMapper.countAll(criteria);
        //查询所有卖出记录
        List<ProductCriteria> criterias = getProductCriteriaListWithSoldList(solds);
        List<Product> products = productMapper.getByProductIdList(criterias);
        //封装查询条件以查询商品图片
        List<ProductModel> productModels = BaseConvert.convertProductListToProductModelList(products);
        List<ProductCriteria> productCriterias = BaseConvert.convertProductModelListToProductCriteriaList(productModels);
        //根据查询条件查询商品图片
        List<ProductPicture> productPictures = productPictureMapper.getByProductIdList(productCriterias);
        //将商品图片封装到对应商品中
        BaseConvert.mapProductAndPicture(productModels, productPictures);
        //将商品封装到对应的卖出记录中
        List<SoldModel> soldModels = createSoldModelList(solds, productModels);

        result.setData(soldModels);
        result.setTotalCount(totalCount);
        return result;
    }

    /**
     * 从卖出记录集合中提取商品id并封装到查询条件
     *
     * @param solds
     * @return
     */
    private List<ProductCriteria> getProductCriteriaListWithSoldList(List<Sold> solds) {
        List<ProductCriteria> criterias = new ArrayList<>();
        if (!CollectionUtils.isEmpty(solds)) {
            for (Sold sold : solds) {
                ProductCriteria criteria = new ProductCriteria();
                criteria.setId(sold.getProductId());
                criterias.add(criteria);
            }
        }
        return criterias;
    }

    /**
     * 将查询出来的商品详情封装到soldModel中
     *
     * @param solds
     * @param products
     * @return
     */
    private List<SoldModel> createSoldModelList(List<Sold> solds, List<ProductModel> products) {
        List<SoldModel> soldModels = new ArrayList<>();
        if (!CollectionUtils.isEmpty(solds) && !CollectionUtils.isEmpty(products)) {
            for (Sold sold : solds) {
                for (ProductModel product : products) {
                    if (sold.getProductId() == product.getId()) {
                        SoldModel soldModel = new SoldModel();
                        BeanUtils.copyProperties(sold, soldModel);
                        soldModel.setProduct(product);
                        soldModels.add(soldModel);
                        break;
                    }
                }
            }
        }
        return soldModels;
    }

    /**
     * 将卖出记录实体类型转换为model类型
     *
     * @param solds
     * @return
     */
    private List<SoldModel> convertSoldListToSoldModelList(List<Sold> solds) {
        if (!CollectionUtils.isEmpty(solds)) {
            List<SoldModel> soldModels = new ArrayList<>();
            for (Sold sold : solds) {
                SoldModel soldModel = new SoldModel();
                BeanUtils.copyProperties(sold, soldModel);
                soldModels.add(soldModel);
            }
            return soldModels;
        }
        return new ArrayList<>();
    }

    /**
     * 用户购买触发添加卖出记录
     *
     * @param sold
     */
    public void save(SoldModel sold) {
        soldMapper.save(sold);
    }

    /**
     * 查询卖出记录是否存在
     *
     * @param soldCriteria
     * @return
     */
    public Long countSold(SoldCriteria soldCriteria) {
        return soldMapper.countSold(soldCriteria);
    }

    /**
     * 删除指定id卖出记录
     *
     * @param soldModel
     */
    public void delete(SoldModel soldModel) {
        soldMapper.delete(soldModel);
    }
}
