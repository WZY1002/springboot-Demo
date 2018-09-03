package javaconfig.dao;

import javaconfig.model.UserPO;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public List<UserPO> querUserList(){
        List<UserPO> result=new ArrayList<UserPO>();
        for (int i=0;i<9;i++){
            UserPO user=new UserPO();
            user.setUsername("username_" + i);
            user.setPassword("password_" + i);
            user.setAge(i + 1);
            result.add(user);
        }
        return result;
    }
}
