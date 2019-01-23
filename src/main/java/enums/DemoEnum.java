package enums;

public enum  DemoEnum {
    BC(1, "1q"),
    BE(2, "2q"),
    C(3, "3q"),
    B(4, "4q"),
    E(5, "5q"),
    EBC(6, "6q"),
    EE(7, "7q")
            ;

    private int code;

    private String name;

    DemoEnum(int code, String name) {
        this.code = code;
        this.name = name;
        System.out.println(code+name);
    }
    public static int getCode(String name){
        for(DemoEnum e : DemoEnum.values()){
            if(e.name.equals(name)){
                return e.code;
            }
        }
        return 0;
    }
}
