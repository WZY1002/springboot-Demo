package mybatisconfig.typetwo;


import mybatisconfig.model.UserPO;

import java.util.List;

public interface UserDAO {

    void insert(UserPO userPO);

    List<UserPO> getAll();

    UserPO getOne(String name);
}
