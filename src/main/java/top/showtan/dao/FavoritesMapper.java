package top.showtan.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.showtan.entity.Favorites;
import top.showtan.model.FavoritesModel;
import top.showtan.model.criteria.FavoritesCriteria;

import java.util.List;

@Repository
public interface FavoritesMapper {
    /**
     * 根据条件查询收藏商品数目
     *
     * @param criteria
     * @return
     */
    Long countFavoriets(FavoritesCriteria criteria);

    /**
     * 用户收藏操作
     *
     * @param favorite
     */
    void save(FavoritesModel favorite);

    /**
     * 取消收藏
     *
     * @param favorite
     */
    void delete(FavoritesModel favorite);

    /**
     * 查询收藏总数目
     *
     * @return
     */
    Long countAll(FavoritesCriteria criteria);

    /**
     * 查询收藏商品集合
     *
     * @param criteria
     * @param page
     * @param pageSize
     * @return
     */
    List<Favorites> search(@Param("criteria") FavoritesCriteria criteria,
                           @Param("page") Long page,
                           @Param("pageSize") Long pageSize);
}
