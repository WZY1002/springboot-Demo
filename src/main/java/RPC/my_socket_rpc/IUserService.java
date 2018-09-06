package RPC.my_socket_rpc;

import RPC.my_socket_rpc.model.UserVO;

public interface IUserService {

    public void sayHello(String name);

    public void sayHello(UserVO user);

    public UserVO getUser(Integer id);

}
