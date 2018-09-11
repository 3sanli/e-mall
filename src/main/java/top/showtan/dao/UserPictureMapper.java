package top.showtan.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.showtan.entity.UserPicture;

@Repository
public interface UserPictureMapper {
    /**
     * 根据用户id获取用户头像
     *
     * @param userId
     * @return
     */
    String getByUserId(Integer userId);

    /**
     * 保存用户图片
     *
     * @param userPicture
     */
    void save(UserPicture userPicture);

    /**
     * 更新指定用户id下的头像
     *
     * @param userId
     * @param picture
     */
    void modify(@Param("userId") Integer userId,
                @Param("picture") String picture);
}
