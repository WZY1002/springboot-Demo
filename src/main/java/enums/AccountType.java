package enums;

public enum  AccountType {
    SAVING, FIXED, CURRENT;
    //自动会加上如下段static代码，初始化枚举中的每个具体类型。（并将所有具体类型放到一个$VALUE数组中，以便用序号访问具体类型）
//    static{
//        SAVING = new AccountType("SAVING", 0);
//    ...  CURRENT = new AccountType("CURRENT", 0);
//        $VALUES = new AccountType[]{
//                SAVING, FIXED, CURRENT
//        } }


    AccountType() {
        System.out.println("枚举构造函数内容");
    }
}
