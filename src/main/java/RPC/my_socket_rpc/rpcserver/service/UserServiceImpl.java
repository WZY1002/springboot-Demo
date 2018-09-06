package RPC.my_socket_rpc.rpcserver.service;

import RPC.my_socket_rpc.IUserService;
import RPC.my_socket_rpc.model.UserVO;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {


    public UserServiceImpl() {
    }

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
