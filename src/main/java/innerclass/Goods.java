package innerclass;

public class Goods {
    private static String good1="电视机";


    public static class getGood1{
        String dianshi=good1;
    }

    private static class getFoods implements Foods{
        @Override
        public String getFruit() {
            return "香蕉";
        }

        @Override
        public String getMeat() {
            return "牛肉";
        }

        @Override
        public String getRice() {
            return "东北大米"+(new getGood1().dianshi);
        }
    }

    public static void main(String[] args) {
        System.out.println(new getGood1().dianshi);
        new getFoods(){
            {
                getFruit();
            }
        };

    }

    public String breakfast(){
        getFoods food=new getFoods();
        return "早餐是"+food.getRice()+food.getMeat()+food.getFruit();
    }

}
