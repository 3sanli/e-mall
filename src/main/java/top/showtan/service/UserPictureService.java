package top.showtan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.showtan.dao.UserPictureMapper;

@Service
@Transactional
public class UserPictureService {
    @Autowired
    private UserPictureMapper userPictureMapper;

    public String getByUserId(Integer userId){
        return userPictureMapper.getByUserId(userId);
    }

}
