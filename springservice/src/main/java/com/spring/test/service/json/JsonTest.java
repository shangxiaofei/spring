package com.spring.test.service.json;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import sun.rmi.runtime.Log;

import io.netty.handler.codec.json.JsonObjectDecoder;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午8:25 2018/7/19
 */
public class JsonTest {

    public static void main(String[] args) {
        testParse();
    }

    public static  void testParse(){
        String jsonStr="[{\n" +
                "    \"type\": \"amount\",\n" +
                "    \"private\": {\n" +
                "        \"upper\": 100000000000,\n" +
                "        \"lower\": 500000\n" +
                "    },\n" +
                "    \"public\": {\n" +
                "        \"upper\":100000000000,\n" +
                "        \"lower\": 1000000\n" +
                "    }\n" +
                "}]";

        JSONArray jsonArray=JSONObject.parseArray(jsonStr);


        for(int i=0;i<jsonArray.size();i++){
           JSONObject jsonObject= (JSONObject) jsonArray.get(i);
            System.out.println("类型==>"+jsonObject.getString("type"));
            JSONObject a= (JSONObject) jsonObject.get("private");
            System.out.println("对私==>"+ a.get("upper"));

            long upper=a.getLong("upper");
            long lower=a.getLong("lower");
            System.out.println("付款金额  最大"+upper+",最小"+lower);
        }
    }
}
