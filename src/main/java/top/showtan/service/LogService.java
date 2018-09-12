package top.showtan.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import top.showtan.dao.CommentMapper;
import top.showtan.dao.LogMapper;
import top.showtan.dao.ProductMapper;
import top.showtan.dao.ProductPictureMapper;
import top.showtan.entity.Comment;
import top.showtan.entity.Log;
import top.showtan.entity.Product;
import top.showtan.entity.ProductPicture;
import top.showtan.model.CommentModel;
import top.showtan.model.LogModel;
import top.showtan.model.ProductModel;
import top.showtan.model.criteria.LogCriteria;
import top.showtan.model.criteria.ProductCriteria;
import top.showtan.util.BaseConvert;
import top.showtan.util.PageModel;
import top.showtan.util.PageUtil;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional

public class LogService {
    @Autowired
    private LogMapper logMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private ProductPictureMapper productPictureMapper;

    /**
     * 分页查询日志信息
     *
     * @param criteria
     * @param page
     * @param pageSize
     * @return
     */
    public PageModel<LogModel> search(LogCriteria criteria, Long page, Long pageSize) {
        PageModel<LogModel> result = new PageModel<>();
        PageUtil pageUtil = new PageUtil(page, pageSize);
        List<Log> logs = logMapper.search(criteria, pageUtil.getSkip(), pageUtil.getTake());
        Long totalCount = logMapper.countAll(criteria);

        List<LogModel> logModels = convertLogListToLogModelList(logs);

        //查询该日志下所涉及到的商品信息并封装到各日志下
        List<ProductCriteria> productCriterias = getProductCriteriaListWithLogList(logs);
        List<Product> products = productMapper.getByProductIdList(productCriterias);
        List<ProductModel> productModels = BaseConvert.convertProductListToProductModelList(products);

        //查询各商品的图片并封装到各商品model下
        List<ProductPicture> productPictures = productPictureMapper.getByProductIdList(productCriterias);
        BaseConvert.mapProductAndPicture(productModels, productPictures);
        mapProductModelsWithLogModels(logModels, productModels);

        //将该日志涉及到的交易评论封装到日志下
        List<Comment> comments = commentMapper.getByProductIds(productCriterias);
        List<CommentModel> commentModels = BaseConvert.convertCommentListToCommentModelList(comments);
        mapCommentsWithLogModels(logModels, commentModels);

        result.setData(logModels);
        result.setTotalCount(totalCount);
        return result;
    }

    /**
     * 将Log实体类集合封装到LogModel集合中
     *
     * @param logs
     * @return
     */
    private List<LogModel> convertLogListToLogModelList(List<Log> logs) {
        if (!CollectionUtils.isEmpty(logs)) {
            List<LogModel> logModels = new ArrayList<>();
            for (Log log : logs) {
                LogModel logModel = new LogModel();
                BeanUtils.copyProperties(log, logModel);
                logModels.add(logModel);
            }
            return logModels;
        }
        return new ArrayList<>();
    }

    /**
     * 从Log集合中提取商品id集合
     *
     * @param logs
     * @return
     */
    private List<ProductCriteria> getProductCriteriaListWithLogList(List<Log> logs) {
        List<ProductCriteria> criterias = new ArrayList<>();
        if (!CollectionUtils.isEmpty(logs)) {
            for (Log log : logs) {
                ProductCriteria criteria = new ProductCriteria();
                criteria.setId(log.getProductId());
                criterias.add(criteria);
            }
        }
        return criterias;
    }

    /**
     * 将各日志封装对应的productmodel
     *
     * @param logModels
     * @param productModels
     */
    private void mapProductModelsWithLogModels(List<LogModel> logModels, List<ProductModel> productModels) {
        for (LogModel logModel : logModels) {
            for (ProductModel productModel : productModels) {
                if (productModel.getId() == logModel.getProductId()) {
                    logModel.setProduct(productModel);
                    break;
                }
            }
        }
    }

    /**
     * 将各日志封装对应的comment
     *
     * @param logModels
     * @param comments
     */
    private void mapCommentsWithLogModels(List<LogModel> logModels, List<CommentModel> comments) {
        for (LogModel logModel : logModels) {
            for (CommentModel comment : comments) {
                if (comment.getProductId() == logModel.getProductId() && comment.getCreatorId() != logModel.getCreatorId()) {
                    logModel.setComment(comment);
                    break;
                }
            }
        }
    }
}
