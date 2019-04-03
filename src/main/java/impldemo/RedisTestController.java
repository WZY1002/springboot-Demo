//package demo;
//
//
////import redis.myredis.singleconfig.RedisClientSingle;
//import mybatisconfig.dao.UserDAO;
//import mybatisconfig.model.UserPO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import com.google.gson.Gson;
//import rabbitmq.producer.DoMqProducer;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.List;
//
//@RestController
//@RequestMapping(value = "/test")
//public class RedisTestController {
//
//    private static final String key = "wms:test:redis";
//
////    @Autowired
////    private RedisClientSingle redisClientSingle;
//
//    @Autowired
//    private UserDAO userDAO;
//
//    @Autowired
//    private DoMqProducer producer;
//
//    private Gson gson=new Gson();
//
//    /**
//     * redis测试
//     * @author  wzy
//     * @param
//     * @return
//     * @create  2018/1/3 10:22
//     **/
//    @RequestMapping(value = "/redis")
//    public String setredis(){
//        Long reuslt = redisClientSingle.setnx(key,"1");
//        redisClientSingle.expire(key,600);
//        return "hello" + reuslt;
//    }
//
//    /**
//     * mybatis测试
//     * @author  wzy
//     * @param
//     * @return
//     * @create  2018/1/3 10:22
//     **/
//    @RequestMapping(value = "/doinsert", produces = "application/json")
//    public void  doinsert(@RequestBody(required = false) String jsoeen,HttpServletRequest request){
//        String json="{\n" +
//                "    \"username\": \"wzy\",\n" +
//                "    \"password\": \"wzy123\"\n" +
//                "}";
//        UserPO param=gson.fromJson(json,UserPO.class);
//        userDAO.insert(param);
//    }
//
//    /**
//     * mybatis测试
//     * @author  wzy
//     * @param
//     * @return
//     * @create  2018/1/3 10:22
//     **/
//    @RequestMapping(value = "/doselect",produces = "application/json")
//    public List<UserPO> doselect(@RequestBody(required = false) String json,HttpServletRequest request){
//        List<UserPO> list=userDAO.getAll();
//        return list;
//    }
//
//    /**
//     * 查询一个
//     * @author  wzy
//     * @create  2018/1/4 11:03
//     **/
//    @RequestMapping(value = "/dofind",produces = "application/json")
//    public UserPO dofind(@RequestBody(required = false) String json,HttpServletRequest request){
//        String name=json;
//        UserPO userPO=userDAO.getOne(name);
//        return userPO;
//    }
//
//    /**
//     * mq测试
//     * @author  wzy
//     * @param
//     * @return
//     * @create  2018/1/3 10:22
//     **/
//    @RequestMapping(value = "/mqtest")
//    public void domq(){
//        producer.send();
//    }
//
//    /**
//     * json测试
//     * @author  wzy
//     * @create  2018/1/3 11:36
//     **/
//    @RequestMapping(value = "/jsontest")
//    public String jsontest(@RequestBody(required = false) String json,HttpServletRequest request){
//        System.out.println(json);
//        return json;
//    }
//
//    /**
//     * 请求内容是一个json串,spring会自动把他和我们的参数bean对应起来,不过要加@RequestBody注解
//     * @return
//     */
//    @RequestMapping(value = "/loginbypost2",produces = "application/json")
//    public String loginByPost2(@RequestBody UserPO userPO) {
//        System.out.println(userPO.username);
//        System.out.println(userPO.password);
//        return userPO.username;
//    }
//
//
//
//
//}
