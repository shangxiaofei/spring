package com.spring.test.service.javacore.collections;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 上午10:59 2018/12/15
 */
public class LoadBalance {

    public static void main(String[] args) {

    }

    public String loadBalanceChannel(){
        //分组值最小的通道集合
        List<String> bestMinGroupChannels=new ArrayList<>();
        //到账时间最短的最优通道
        String bestChannel="";
        //最短到账时效
        double minEta=Double.MAX_VALUE;
        for(String ch:bestMinGroupChannels){
            //ch到账时长
            double eta=0D;
            if(eta<minEta){
                minEta=eta;
                bestChannel=ch;
            }
        }
        return bestChannel;
    }


    public String loadBalanceChannelV2(){
        //分组值最小的通道集合
        List<String> bestMinGroupChannels=new ArrayList<>();

        //到账时间最短的最优秀通道
        String bestChannel=null;
        //最短到账时效
        double minEta=Double.MAX_VALUE;
        double mark=7*24*60;//用一星期的时长作为一个可用通道和不可用通道的分割线

        for(String ch:bestMinGroupChannels){

            //ch到账时长
            double eta=0D;

            if(ch!="可用"){
                eta+=mark;
            }

            if(eta<minEta){
                minEta=eta;
                bestChannel=ch;
            }

        }
        return bestChannel;
    }
}
