package top.showtan.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.showtan.model.criteria.UserCriteria;

@Repository
public interface UserMapper {
    /**
     * 统计用户钱财
     *
     * @param criteria
     * @return
     */
    Long countMoney(UserCriteria criteria);

    /**
     * 用户购买商品平台代为扣钱
     *
     * @param userId
     * @param money
     */
    void costMoney(@Param("userId") Integer userId,
                   @Param("money") Long money);

    /**
     * 交易完成平台转账以及交易未成功完成平台退款
     * @param userId
     * @param money
     */
    void addMoney(@Param("userId") Integer userId,
                  @Param("money") Long money);
}
