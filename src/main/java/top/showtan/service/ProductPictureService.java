package top.showtan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.showtan.dao.ProductPictureMapper;
import top.showtan.entity.ProductPicture;

import java.util.List;

@Service
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
