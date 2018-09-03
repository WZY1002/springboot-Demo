package mybatisconfig.typeone.dao;


import mybatisconfig.model.UserPO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;


import java.util.List;

public interface UserDAO {

    @Insert("INSERT INTO user(username,password) VALUES(#{username}, #{password})")
    void insert(UserPO userPO);

    @Select("SELECT * FROM user")
    List<UserPO> getAll();

    @Select("SELECT * FROM user WHERE username = #{name}")
    UserPO getOne(String name);
}
