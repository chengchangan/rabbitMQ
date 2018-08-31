package message;

/**
 * Created by Administrator on 2018/4/21.
 */
public class StudentMessage extends Message {

    private String name;

    private Integer age;

    @Override
    public String exchange() {
        return "com.cca.topic";
    }

    @Override
    public String routingKey() {
        return "com.cca.test.create";
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
        return "StudentMessage{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
