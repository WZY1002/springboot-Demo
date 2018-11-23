package commom;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author Chengw
 * @since 2017/12/5 09:23.
 */
public class GsonUtils {
	
    /**
     * gson 转换静态类
     */
    public static Gson gson = new GsonBuilder().serializeNulls().create();
    
    /**
     * gson 转换静态类
     */
    public static Gson gsonNotNull = new GsonBuilder().create();
    
    /**
     * gson 转换静态类
     */
    public static Gson dataFormatGson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
}
