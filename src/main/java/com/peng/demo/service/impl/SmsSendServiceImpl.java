package com.peng.demo.service.impl;

import com.cloopen.rest.sdk.BodyType;
import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.peng.demo.service.SmsSendService;
import org.junit.Test;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Random;
import java.util.Set;

@Service
public class SmsSendServiceImpl implements SmsSendService {
    @Test
    @Override
    public void smsSend() {
        //请求地址
        String serverIp = "app.cloopen.com";
        //请求端口
        String serverPort = "8883";
        //账号信息
        String accountSID = "";
        String accountToken = "";
        //应用AppID
        String appId = "";

        CCPRestSmsSDK sdk = new CCPRestSmsSDK();
        sdk.init(serverIp, serverPort);
        sdk.setAccount(accountSID, accountToken);
        sdk.setAppId(appId);
        sdk.setBodyType(BodyType.Type_JSON);

        String to = "";
        //短信模板1   4位数验证码   5分钟有效
        String templateId = "1";
        String[] datas = {randCode(4), "5"};

        //可选   subAppend 扩展码    reqId 第三方自定义消息id
        HashMap<String, Object> result = sdk.sendTemplateSMS(to, templateId, datas);

        if ("000000".equals(result.get("statusCode"))) {
            //正常返回输出data包信息(map)
            HashMap<String, Object> data = (HashMap<String, Object>) result.get("data");
            Set<String> keySet = data.keySet();
            for (String key : keySet) {
                Object object = data.get(key);
                System.out.println(key + "=" + object);
            }
        } else {
            //异常返回输出错误码和错误信息
            System.out.println("错误码=" + result.get("statusCode") + "错误信息=" + result.get("statusMsg"));
        }
    }

    //随机验证码
    public String randCode(Integer len) {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            //产生0-9的随机数
            int nextInt = random.nextInt(10);
            builder.append(String.valueOf(nextInt));
        }
        return builder.toString();
    }
}
