package com.zxzyyyy.loveu.response;

import com.zxzyyyy.loveu.entity.Auth;

import java.util.HashMap;

/**
 * @Description:
 */
public class Data extends HashMap {

    public Data addObj(String key, Object value){
        this.put(key, value);
        return this;
    }

    public Data() {

    }

    public Data(Auth auth) {
        this.put("username", auth.getUsername());
        this.put("groupName", auth.getGroup().getGroupName());
    }

    public Data addChat(String chat){
        this.put("chat", chat);
        return this;
    }

    public Data addAmount(String amount){
        this.put("amount", amount);
        return this;
    }

    public Data addOutTradeNo(String outTradeNo){
        this.put("outTradeNo", outTradeNo);
        return this;
    }

    public Data addActualProfit(String actualProfit){
        this.put("actualProfit", actualProfit);
        return this;
    }

    public Data addTradeStatus(String tradeStatus){
        this.put("tradeStatus", tradeStatus);
        return this;
    }

    public Data addPresentGiftUuid(String presentGiftUuid){
        this.put("presentGiftUuid", presentGiftUuid);
        return this;
    }

}
