package top.showtan.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.showtan.entity.Log;
import top.showtan.model.LogModel;
import top.showtan.model.criteria.LogCriteria;

import java.util.List;

@Repository
public interface LogMapper {
    /**
     * 查询指定用户一段时间内的买入卖出记录
     *
     * @param criteria
     * @return
     */
    List<Log> search(@Param("criteria") LogCriteria criteria,
                     @Param("skip") Long skip,
                     @Param("take") Long take);

    /**
     * 根据条件查询该条件下所有记录数
     *
     * @param criteria
     * @return
     */
    Long countAll(LogCriteria criteria);

    /**
     * 保存用户记录
     *
     * @param logModel
     */
    void save(LogModel logModel);
}
