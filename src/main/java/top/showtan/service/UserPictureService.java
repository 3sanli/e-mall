package top.showtan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.showtan.dao.UserPictureMapper;

@Service
public class UserPictureService {
    @Autowired
    private UserPictureMapper userPictureMapper;

    public String getByUserId(Integer userId){
        return userPictureMapper.getByUserId(userId);
    }

}
