package top.showtan.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.showtan.dao.ProductMapper;
import top.showtan.dao.UserMapper;
import top.showtan.dao.UserPictureMapper;
import top.showtan.entity.Product;
import top.showtan.entity.User;
import top.showtan.entity.UserPicture;
import top.showtan.model.BuyModel;
import top.showtan.model.UserModel;
import top.showtan.model.criteria.ProductCriteria;
import top.showtan.model.criteria.UserCriteria;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
@Transactional
public class UserService {
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private UserPictureMapper userPictureMapper;

    /**
     * 获取本次登录对象信息
     *
     * @return
     */
    public UserModel getCurrentUser() {
        //TO LEARN
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        UserModel user = (UserModel) session.getAttribute("user");
        if (user == null) {
            return new UserModel();
        }
        return user;
    }

    /**
     * 根据条件查询指定用户（用户唯一用户名/用户手机号）
     *
     * @param criteria
     * @return
     */
    public UserModel search(UserCriteria criteria) {
        User user = userMapper.search(criteria);
        if (user == null) {
            return null;
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(user, userModel);
        userModel.setPicture(userPictureMapper.getByUserId(userModel.getId()));
        return userModel;
    }

    /**
     * 用户更改自身信息
     *
     * @param user
     */
    public void modify(UserModel user) {
        //用户不予直接更新信誉值
        user.setCredit(null);
        userMapper.modify(user);
//        userPictureMapper.modify(user.getId(),user.getPicture());
    }

    /**
     * 用户成功注册，将用户信息持久化到数据库中
     *
     * @param user
     */
    public void save(UserModel user) {
        sqlSessionTemplate.insert("top.showtan.dao.UserMapper.save", user);
        UserPicture userPicture = new UserPicture();
        userPicture.setUserId(user.getId());
        userPicture.setPicture(user.getPicture());
        userPictureMapper.save(userPicture);
    }

    /**
     * 用户购买商品后扣除相关钱财
     *
     * @param buyModel
     */
    public Boolean costMoney(BuyModel buyModel) {
        ProductCriteria productCriteria = new ProductCriteria();
        productCriteria.setId(buyModel.getProductId());
        Product product = productMapper.getById(productCriteria);

        UserCriteria userCriteria = new UserCriteria();
        userCriteria.setId(buyModel.getCreatorId());

        Long money = countMoney(userCriteria);

        if (money < product.getPrice()) {
            return false;
        }
        userMapper.costMoney(buyModel.getCreatorId(), product.getPrice());
        return true;
    }

    /**
     * 计算用户资产
     *
     * @param criteria
     * @return
     */
    private Long countMoney(UserCriteria criteria) {
        return userMapper.countMoney(criteria);
    }

    public void addMoney(Integer userId, Long money) {
        userMapper.addMoney(userId, money);
    }
}
