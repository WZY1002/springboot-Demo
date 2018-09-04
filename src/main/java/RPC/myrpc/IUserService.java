package RPC.myrpc;

import RPC.myrpc.model.UserVO;

public interface IUserService {

    public void sayHello(String name);

    public void sayHello(UserVO user);

    public UserVO getUser(Integer id);

}
