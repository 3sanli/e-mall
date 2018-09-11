package top.showtan.util;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import top.showtan.entity.Comment;
import top.showtan.entity.Product;
import top.showtan.entity.ProductPicture;
import top.showtan.model.CommentModel;
import top.showtan.model.ProductModel;
import top.showtan.model.criteria.ProductCriteria;

import java.util.ArrayList;
import java.util.List;

public class BaseConvert {
    /**
     * 将product集合转换为productModel集合
     *
     * @param products
     * @return
     */
    public static List<ProductModel> convertProductListToProductModelList(List<Product> products) {
        if (!CollectionUtils.isEmpty(products)) {
            List<ProductModel> productModels = new ArrayList<>();
            for (Product product : products) {
                ProductModel productModel = new ProductModel();
                BeanUtils.copyProperties(product, productModel);
                productModels.add(productModel);
            }
            return productModels;
        }
        return new ArrayList<>();
    }

    /**
     * 将productModel集合转换为productCriteria集合
     *
     * @param productModels
     * @return
     */
    public static List<ProductCriteria> convertProductModelListToProductCriteriaList(List<ProductModel> productModels) {
        if (!CollectionUtils.isEmpty(productModels)) {
            List<ProductCriteria> criterias = new ArrayList<>();
            for (ProductModel productModel : productModels) {
                ProductCriteria criteria = new ProductCriteria();
                BeanUtils.copyProperties(productModel, criteria);
                criterias.add(criteria);
            }
            return criterias;
        }
        return new ArrayList<>();
    }

    /**
     * 将图片封装到productmodel集合中
     *
     * @param productModels
     * @param pictures
     */
    public static void mapProductAndPicture(List<ProductModel> productModels, List<ProductPicture> pictures) {
        for (ProductModel productModel : productModels) {
            for (ProductPicture productPicture : pictures) {
                if (productPicture.getProductId() == productModel.getId()) {
                    productModel.getPictures().add(productPicture);
                }
            }
        }
    }

    /**
     * 将CommentList转换为CommentModelList
     *
     * @param comments
     * @return
     */
    public static List<CommentModel> convertCommentListToCommentModelList(List<Comment> comments) {
        if (!CollectionUtils.isEmpty(comments)) {
            List<CommentModel> commentModels = new ArrayList<>();
            for (Comment comment : comments) {
                CommentModel commentModel = new CommentModel();
                BeanUtils.copyProperties(comment, commentModel);
                commentModels.add(commentModel);
            }
            return commentModels;
        }
        return new ArrayList<>();
    }
}
