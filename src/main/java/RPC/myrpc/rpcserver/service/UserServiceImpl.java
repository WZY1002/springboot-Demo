package RPC.myrpc.rpcserver.service;

import RPC.myrpc.IUserService;
import RPC.myrpc.model.UserVO;

public class UserServiceImpl implements IUserService {
    @Override
    public void sayHello(String name) {
        System.out.println( " hello " + name);
    }

    @Override
    public void sayHello(UserVO user) {
        System.out.println( " hello " + user.getName());
    }

    @Override
    public UserVO getUser(Integer id) {
        UserVO user = new UserVO("wzy", 0, "男");
        return user;
    }
}
