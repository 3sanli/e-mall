package top.showtan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.showtan.dao.ProductPictureMapper;
import top.showtan.entity.ProductPicture;

import java.util.List;

@Service
@Transactional
public class ProductPictureService {
    @Autowired
    private ProductPictureMapper productPictureMapper;

    /**
     * 保存商品图片（集）
     * @param productPictures
     */
    public void save(List<ProductPicture> productPictures){
        productPictureMapper.save(productPictures);
    }



}
