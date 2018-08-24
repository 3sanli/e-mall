package top.showtan.dao;

import org.springframework.stereotype.Repository;
import top.showtan.entity.Comment;
import top.showtan.model.CommentModel;
import top.showtan.model.criteria.CommentCriteria;
import top.showtan.model.criteria.ProductCriteria;

import java.util.List;

@Repository
public interface CommentMapper {
    /**
     * 判断是否已经评论
     *
     * @param criteria
     * @return
     */
    Long countComment(CommentCriteria criteria);

    /**
     * 用户评论
     *
     * @param comment
     */
    void save(CommentModel comment);

    /**
     * 根据商品id查询商品评论
     *
     * @param criteriaList
     * @return
     */
    List<Comment> getByProductIds(List<ProductCriteria> criteriaList);
}
