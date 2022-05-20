package lsieun.buddy.template;

import java.util.Date;

public class Celebrity {
    public static final String MOTTO = "抛弃时间的人，时间也抛弃他。——莎士比亚";

    private String name;
    private int age;
    private Date birthDay;

    public Celebrity(String name, int age, Date birthDay) {
        this.name = name;
        this.age = age;
        this.birthDay = birthDay;
    }

    public static int add(int a, int b) {
        return a + b;
    }
}
