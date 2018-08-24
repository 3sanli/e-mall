package top.showtan.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.showtan.entity.Buy;
import top.showtan.model.BuyModel;
import top.showtan.model.criteria.BuyCriteria;

import java.util.List;

@Repository
public interface BuyMapper {
    /**
     * 查看自身是否购买该商品
     *
     * @param criteria
     * @return
     */
    Long countBuy(BuyCriteria criteria);

    /**
     * 保存用户购买操作
     *
     * @param buy
     */
    void save(BuyModel buy);

    /**
     * 用户操作引起的购买记录状态的改变
     *
     * @param buy
     */
    void updateBuy(BuyModel buy);

    /**
     * 用户删除购买记录
     *
     * @param buy
     */
    void delete(BuyModel buy);

    /**
     * 根据条件查询购买记录集
     *
     * @param criteria
     * @param page
     * @param pageSize
     * @return
     */
    List<Buy> search(@Param("criteria") BuyCriteria criteria,
                     @Param("page") Long page,
                     @Param("pageSize") Long pageSize);

    /**
     * 查询总购买记录数
     *
     * @return
     */
    Long countAll(BuyCriteria criteria);

    /**
     * 根据商品id查询唯一交易记录
     *
     * @param criteria
     * @return
     */
    Buy getByProductId(BuyCriteria criteria);

    /**
     * 根据id查询唯一购买记录
     *
     * @param id
     * @return
     */
    Buy getById(Integer id);
}
