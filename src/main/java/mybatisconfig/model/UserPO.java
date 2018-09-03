package mybatisconfig.model;

import lombok.Data;

@Data
public class UserPO {
    public String username;

    public String password;

    @Override
    public String toString() {
        return "UserPO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
