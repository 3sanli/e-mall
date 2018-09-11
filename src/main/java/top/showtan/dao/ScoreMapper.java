package top.showtan.dao;

import org.springframework.stereotype.Repository;
import top.showtan.model.ScoreModel;

@Repository
public interface ScoreMapper {
    /**
     * 保存用户评分
     *
     * @param score
     */
    void save(ScoreModel score);

    /**
     * 获取指定用户当前平均信誉值
     *
     * @param toId
     * @return
     */
    Integer mark(Integer toId);
}
