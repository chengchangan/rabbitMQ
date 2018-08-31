package message;

/**
 * Created by Administrator on 2018/4/21.
 */
public class UserMessage extends Message {

    private String name;

    private Integer age;

    @Override
    public String exchange() {
        return "com.cca.fanout";
    }

    @Override
    public String routingKey() {
        return "com.cca.test.fanout";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserMessage{" +
            "name='" + name + '\'' +
            ", age=" + age +
            '}';
    }
}
