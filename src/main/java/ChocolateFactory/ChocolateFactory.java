package ChocolateFactory;

public class ChocolateFactory {

    private Boolean empty;

    private Boolean boiled;

    public  static ChocolateFactory uniqueInstance=new ChocolateFactory();

    private ChocolateFactory(){
        empty=true;
        boiled=false;
    }

    public static ChocolateFactory getInstance() {
        if(uniqueInstance==null){
            uniqueInstance=new ChocolateFactory();
        }
        return uniqueInstance;
    }

    //添加巧克力原料
    public  void  fill(){
        if(empty){
            empty=false;
            boiled=false;
        }
    }

    //煮沸
    public  void boil(){
        if((!empty) && (!boiled)){
            boiled=true;
        }
    }

    //排出巧克力
    public  void drain(){
        if((!empty) && boiled){
            empty=true;
        }
    }

}
