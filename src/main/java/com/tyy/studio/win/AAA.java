package com.tyy.studio.win;

import java.net.URLEncoder;
import java.util.Base64;

import com.alibaba.fastjson.JSON;

public class AAA {

    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws Exception {
        String a = "{\r\n" + "    \"product\": \"thing_topology\",\r\n" + "    \"type\": \"js_httpserver_1_0\",\r\n" + "    \"diagramId\": \"56284564e99dc820\"\r\n" + "}";
        byte[] encode = Base64.getEncoder().encode(a.getBytes("utf-8"));
        String b = new String(encode, "utf-8");
        System.out.println(b);
        String c = URLEncoder.encode(b, "utf-8");
        System.out.println(c);
    }

}
