package com.cca.message;

import com.cca.constants.Constants;

public class DelayMessage extends Message {

    private String message;


    @Override
    public String exchange() {
        return Constants.DIRECT_EXCHANGE;
    }

    @Override
    public String routingKey() {
        return Constants.DIRECT_NORMAL_BINDING_KEY;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
