package top.showtan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.showtan.dao.ProductMapper;
import top.showtan.dao.UserMapper;
import top.showtan.entity.Product;
import top.showtan.model.BuyModel;
import top.showtan.model.criteria.ProductCriteria;
import top.showtan.model.criteria.UserCriteria;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProductMapper productMapper;

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

    public void addMoney(Integer userId,Long money) {
        userMapper.addMoney(userId, money);
    }
}
