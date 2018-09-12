package top.showtan.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import top.showtan.dao.*;
import top.showtan.entity.Buy;
import top.showtan.entity.Comment;
import top.showtan.entity.Product;
import top.showtan.entity.ProductPicture;
import top.showtan.model.BuyModel;
import top.showtan.model.CommentModel;
import top.showtan.model.LogModel;
import top.showtan.model.ProductModel;
import top.showtan.model.criteria.BuyCriteria;
import top.showtan.model.criteria.ProductCriteria;
import top.showtan.util.BaseConvert;
import top.showtan.util.BuyStatus;
import top.showtan.util.PageModel;
import top.showtan.util.PageUtil;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BuyService {
    @Autowired
    private BuyMapper buyMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductPictureMapper productPictureMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private LogMapper logMapper;

    /**
     * 根据条件查询商品数目
     *
     * @param criteria
     * @return
     */
    public Long countBuy(BuyCriteria criteria) {
        return buyMapper.countBuy(criteria);
    }

    /**
     * 用户购买操作，保存购买记录
     *
     * @param buy
     */
    public void save(BuyModel buy) {
        buyMapper.save(buy);
    }

    /**
     * 用户取消购买,将购买记录改为‘废弃’状态
     *
     * @param buy
     */
    public void cancelBuy(BuyModel buy) {
        buy.setStatus(BuyStatus.ABANDONED);
        buyMapper.updateBuy(buy);
    }

    /**
     * 用户确认收货
     *
     * @param buy
     */
    public void complete(BuyModel buy) {
        buy.setStatus(BuyStatus.COMPLETED);
        buyMapper.updateBuy(buy);

        //将此次记录到log表中
        LogModel logModel = new LogModel();
        BeanUtils.copyProperties(buy,logModel);
        logModel.setType(0);
        logMapper.save(logModel);
    }

    /**
     * 根据id查询唯一购买记录
     *
     * @param id
     * @return
     */
    public BuyModel getById(Integer id) {
        Buy buy = buyMapper.getById(id);
        if(buy == null){
            return null;
        }
        BuyModel buyModel = new BuyModel();
        BeanUtils.copyProperties(buy, buyModel);
        return buyModel;
    }

    /**
     * 用户删除购买记录
     *
     * @param buy
     */
    public void delete(BuyModel buy) {
        buyMapper.delete(buy);
    }

    /**
     * 查询购买记录
     *
     * @param criteria
     * @param page
     * @param pageSize
     * @return
     */
    public PageModel<BuyModel> search(BuyCriteria criteria, Long page, Long pageSize) {
        PageModel<BuyModel> result = new PageModel<>();
        PageUtil pageUtil = new PageUtil(page, pageSize);
        List<Buy> buys = buyMapper.search(criteria, pageUtil.getSkip(), pageUtil.getTake());
        Long totalCount = buyMapper.countAll(criteria);
        //查询所有已买记录
        List<ProductCriteria> criterias = getProductCriteriaListWithBuyList(buys);
        List<Product> products = productMapper.getByProductIdList(criterias);
        //封装查询条件以查询商品图片
        List<ProductModel> productModels = BaseConvert.convertProductListToProductModelList(products);
        //根据查询条件查询商品图片
        List<ProductPicture> productPictures = productPictureMapper.getByProductIdList(criterias);
        //将商品图片封装到对应商品中
        BaseConvert.mapProductAndPicture(productModels, productPictures);
        //将商品封装到对应的已买记录中
        List<BuyModel> buyModels = createBuyModelList(buys, productModels);

        //查询用户是否对商品进行评论
        List<Comment> comments = commentMapper.getByProductIds(criterias);
        List<CommentModel> commentModels = BaseConvert.convertCommentListToCommentModelList(comments);
        //将评论记录封装到buyModels中
        mapCommentsWithBuyModels(buyModels,commentModels);

        result.setData(buyModels);
        result.setTotalCount(totalCount);
        return result;
    }

    /**
     * 将交易评论分装到buyModels中
     * @param buyModels
     * @param comments
     */
    private void mapCommentsWithBuyModels(List<BuyModel> buyModels,List<CommentModel> comments){
        for(BuyModel buyModel:buyModels){
            for(CommentModel comment:comments){
                if(comment.getProductId() == buyModel.getProductId() && comment.getCreatorId() == buyModel.getCreatorId()){
                    buyModel.setComment(comment);
                    break;
                }
            }
        }
    }

    /**
     * 将查询出来的商品详情封装到buyModel中
     *
     * @param buys
     * @param products
     * @return
     */
    private List<BuyModel> createBuyModelList(List<Buy> buys, List<ProductModel> products) {
        List<BuyModel> buyModels = new ArrayList<>();
        if (!CollectionUtils.isEmpty(buys) && !CollectionUtils.isEmpty(products)) {
            for (Buy buy : buys) {
                for (ProductModel product : products) {
                    if (buy.getProductId() == product.getId()) {
                        BuyModel buyModel = new BuyModel();
                        BeanUtils.copyProperties(buy, buyModel);
                        buyModel.setProduct(product);
                        buyModels.add(buyModel);
                        break;
                    }
                }
            }
        }
        return buyModels;
    }

    /**
     * 从购买记录集合中提取商品id并封装到查询条件
     *
     * @param buys
     * @return
     */
    private List<ProductCriteria> getProductCriteriaListWithBuyList(List<Buy> buys) {
        List<ProductCriteria> criterias = new ArrayList<>();
        if (!CollectionUtils.isEmpty(buys)) {
            for (Buy buy : buys) {
                ProductCriteria criteria = new ProductCriteria();
                criteria.setId(buy.getProductId());
                criterias.add(criteria);
            }
        }
        return criterias;
    }
}
