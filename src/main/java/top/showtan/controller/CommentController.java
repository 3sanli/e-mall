package top.showtan.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.showtan.model.CommentModel;
import top.showtan.model.criteria.CommentCriteria;
import top.showtan.service.CommentService;
import top.showtan.util.Response;

@Controller
@RequestMapping("/product/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @RequestMapping("/save")
    @ResponseBody
    public Response save(@RequestBody CommentModel comment){
        //TODO
        comment.setCreatorId(1);
        comment.setCreatorName("user01");
        CommentCriteria criteria = new CommentCriteria();
        BeanUtils.copyProperties(comment, criteria);
        Long count = commentService.countComment(criteria);
        if (count != 0) {
            return Response.SUCCESS();
        }
        commentService.save(comment);
        return Response.SUCCESS();
    }
}