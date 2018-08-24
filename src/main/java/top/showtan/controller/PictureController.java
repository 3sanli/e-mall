package top.showtan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import top.showtan.util.FastDFSClient;
import top.showtan.util.UploadResult;


@Controller
@RequestMapping("/picture")
public class PictureController {
    private final static String URL_PRE = "http://47.106.244.103:8080/";

    @RequestMapping("/save")
    @ResponseBody
    public UploadResult save(@RequestParam("picture")MultipartFile picture) throws Exception{
        FastDFSClient client = new FastDFSClient("classpath:fdfs.conf");
        String url = URL_PRE + client.uploadFile(picture.getBytes(), getExtName(picture.getOriginalFilename()));
        return UploadResult.SUCCESS(url);
    }

    private String getExtName(String fileName) {
        int i = fileName.lastIndexOf(".") + 1;
        String extName = fileName.substring(i, fileName.length());
        return extName;
    }


}
