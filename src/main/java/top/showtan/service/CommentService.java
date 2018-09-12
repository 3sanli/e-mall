package top.showtan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.showtan.dao.*;
import top.showtan.entity.Buy;
import top.showtan.entity.Product;
import top.showtan.model.CommentModel;
import top.showtan.model.ScoreModel;
import top.showtan.model.UserModel;
import top.showtan.model.criteria.BuyCriteria;
import top.showtan.model.criteria.CommentCriteria;
import top.showtan.model.criteria.ProductCriteria;

@Service
@Transactional
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private BuyMapper buyMapper;

    @Autowired
    private ScoreMapper scoreMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据查询条件判断用户是否已评论
     *
     * @param criteria
     * @return
     */
    public Long countComment(CommentCriteria criteria) {
        return commentMapper.countComment(criteria);
    }

    /**
     * 评论操作
     *
     * @param comment
     */
    public void save(CommentModel comment) {
        commentMapper.save(comment);
        ProductCriteria productCriteria = new ProductCriteria();
        productCriteria.setId(comment.getProductId());
        Product product = productMapper.getById(productCriteria);

        BuyCriteria buyCriteria = new BuyCriteria();
        buyCriteria.setProductId(comment.getProductId());
        Buy buy = buyMapper.getByProductId(buyCriteria);

        ScoreModel score = null;
        //判断评论对象以及评论人
        //商家评论买家
        if (comment.getCreatorId() == product.getCreatorId()) {
            score = new ScoreModel(product.getCreatorId(), product.getCreatorName(), buy.getCreatorId(), buy.getCreatorName(), comment.getCredit());
        }
        //买家评论商家
        else {
            score = new ScoreModel(comment.getCreatorId(), comment.getCreatorName(), product.getCreatorId(), product.getCreatorName(), comment.getCredit());
        }

        scoreMapper.save(score);
        //用户评信誉值过程，后期会封装在别处
        Integer credit = scoreMapper.mark(score.getToId());
        UserModel userModel = new UserModel();
        userModel.setId(score.getToId());
        userModel.setCredit(credit);
        userMapper.modify(userModel);
    }
}
