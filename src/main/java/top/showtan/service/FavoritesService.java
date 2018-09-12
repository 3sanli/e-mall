package top.showtan.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import top.showtan.dao.FavoritesMapper;
import top.showtan.dao.ProductMapper;
import top.showtan.dao.ProductPictureMapper;
import top.showtan.entity.Favorites;
import top.showtan.entity.Product;
import top.showtan.entity.ProductPicture;
import top.showtan.model.FavoritesModel;
import top.showtan.model.ProductModel;
import top.showtan.model.criteria.FavoritesCriteria;
import top.showtan.model.criteria.ProductCriteria;
import top.showtan.util.BaseConvert;
import top.showtan.util.PageModel;
import top.showtan.util.PageUtil;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class FavoritesService {
    @Autowired
    private FavoritesMapper favoritesMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductPictureMapper productPictureMapper;

    /**
     * 根据查询条件判断用户是否已收藏
     *
     * @param criteria
     * @return
     */
    public Long countFavoriets(FavoritesCriteria criteria) {
        return favoritesMapper.countFavoriets(criteria);
    }

    /**
     * 收藏操作
     *
     * @param favorite
     */
    public void save(FavoritesModel favorite) {
        favoritesMapper.save(favorite);
    }

    /**
     * 取消收藏
     *
     * @param favorite
     */
    public void delete(FavoritesModel favorite) {
        favoritesMapper.delete(favorite);
    }

    /**
     * 根据条件查询收藏
     *
     * @param criteria
     * @param page
     * @param pageSize
     * @return
     */
    public PageModel<FavoritesModel> search(FavoritesCriteria criteria, Long page, Long pageSize) {
        PageModel<FavoritesModel> result = new PageModel<>();
        PageUtil pageUtil = new PageUtil(page, pageSize);
        List<Favorites> favorites = favoritesMapper.search(criteria, pageUtil.getSkip(), pageUtil.getTake());
        Long totalCount = favoritesMapper.countAll(criteria);
        //查询所有收藏的商品详情
        List<ProductCriteria> criterias = getProductCriteriaListWithFavoritesList(favorites);
        List<Product> products = productMapper.getByProductIdList(criterias);
        //封装查询条件以查询商品图片
        List<ProductModel> productModels = BaseConvert.convertProductListToProductModelList(products);
        List<ProductCriteria> productCriterias = BaseConvert.convertProductModelListToProductCriteriaList(productModels);
        //根据查询条件查询商品图片
        List<ProductPicture> productPictures = productPictureMapper.getByProductIdList(productCriterias);
        //将商品图片封装到对应商品中
        BaseConvert.mapProductAndPicture(productModels, productPictures);
        //将商品封装到对应的收藏中
        List<FavoritesModel> favoritesModels = createFavoritesModelList(favorites, productModels);

        result.setData(favoritesModels);
        result.setTotalCount(totalCount);
        return result;
    }

    /**
     * 将查询出来的商品详情封装到favoritesModel中
     *
     * @param favorites
     * @param products
     * @return
     */
    private List<FavoritesModel> createFavoritesModelList(List<Favorites> favorites, List<ProductModel> products) {
        List<FavoritesModel> favoritesModelList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(favorites) && !CollectionUtils.isEmpty(products)) {
            for (Favorites favorite : favorites) {
                for (ProductModel product : products) {
                    if (favorite.getProductId() == product.getId()) {
                        FavoritesModel favoritesModel = new FavoritesModel();
                        BeanUtils.copyProperties(favorite, favoritesModel);
                        favoritesModel.setProduct(product);
                        favoritesModelList.add(favoritesModel);
                        break;
                    }
                }
            }
        }
        return favoritesModelList;
    }

    /**
     * 从收藏集合中提取商品id并封装到查询条件
     *
     * @param favorites
     * @return
     */
    private List<ProductCriteria> getProductCriteriaListWithFavoritesList(List<Favorites> favorites) {
        List<ProductCriteria> criterias = new ArrayList<>();
        if (!CollectionUtils.isEmpty(favorites)) {
            for (Favorites favorite : favorites) {
                ProductCriteria criteria = new ProductCriteria();
                criteria.setId(favorite.getProductId());
                criterias.add(criteria);
            }
        }
        return criterias;
    }

    /**
     * 将favorites集合转换为favoritesModel集合
     *
     * @param favorites
     * @return
     */
    private List<FavoritesModel> convertFavoritesListToFavoritesListModelList(List<Favorites> favorites) {
        if (!CollectionUtils.isEmpty(favorites)) {
            List<FavoritesModel> favoritesModels = new ArrayList<>();
            for (Favorites favorite : favorites) {
                FavoritesModel favoritesModel = new FavoritesModel();
                BeanUtils.copyProperties(favorite, favoritesModel);
                favoritesModels.add(favoritesModel);
            }
            return favoritesModels;
        }
        return new ArrayList<>();
    }
}
