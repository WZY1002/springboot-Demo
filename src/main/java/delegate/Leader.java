package delegate;

import java.util.HashMap;
import java.util.Map;

public class Leader {

    private Map<String,ITarget> workMap=new HashMap<>();

    public Leader() {
        workMap.put("销售", new ITargetA());
        workMap.put("写代码", new ITargetA());
    }

    /**
     *按员工分配任务
     * @param command
     */
    public void  dispatch(String command){
        workMap.get(command).doing(command);
    }
}
