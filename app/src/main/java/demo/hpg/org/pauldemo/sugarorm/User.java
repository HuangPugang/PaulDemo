package demo.hpg.org.pauldemo.sugarorm;

import demo.hpg.org.sugar.orm.SugarRecord;

/**
 * Author huarizhong
 * Date 2015/3/11
 * Time 13:55
 */
public class User extends SugarRecord {

    private String name ;
    private int age ;
    private String des;

    public User(){}
    public User(String name,int age ,String des){
        this.name = name;
        this.age = age;
        this.des = des;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", des='" + des + '\'' +
                '}';
    }
}
