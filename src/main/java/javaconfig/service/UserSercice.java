package javaconfig.service;

import javaconfig.dao.UserDAO;
import javaconfig.model.UserPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSercice {
    @Autowired
    private UserDAO userDAO;

    public List<UserPO> querListList(){
        return this.userDAO.querUserList();
    }
}
