package top.showtan.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.showtan.entity.Sold;
import top.showtan.model.SoldModel;
import top.showtan.model.criteria.SoldCriteria;

import java.util.List;

@Repository
public interface SoldMapper {
    /**
     * 添加卖出记录
     *
     * @param sold
     */
    void save(SoldModel sold);

    /**
     * 根据条件动态查询卖出记录
     *
     * @param criteria
     * @param page
     * @param pageSize
     * @return
     */
    List<Sold> search(@Param("criteria") SoldCriteria criteria,
                      @Param("page") Long page,
                      @Param("pageSize") Long pageSize);

    /**
     * 查询所有本人的卖出记录
     *
     * @param criteria
     * @return
     */
    Long countAll(SoldCriteria criteria);

    /**
     * 判断本人是否有指定id的卖出记录
     *
     * @param soldCriteria
     * @return
     */
    Long countSold(SoldCriteria soldCriteria);

    /**
     * 删除指定id的卖出记录s
     *
     * @param soldModel
     */
    void delete(SoldModel soldModel);

    /**
     * 根据id查询指定卖出记录
     *
     * @param id
     * @return
     */
    Sold getById(Integer id);
}
