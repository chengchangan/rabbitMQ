package message;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/4/21.
 */
public abstract class Message implements Serializable {

    public abstract String exchange();

    public abstract String routingKey();


    //测试提交
}
