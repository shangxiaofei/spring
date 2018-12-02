package com.spring.test.service.jsontohtml;

import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午8:25 2018/8/13
 */
public enum  ChBindSC {
    CH(0,new int[]{99999999},"S",Type.JSONARRAY,Type.JSONOBJECT,"NONE"),
    NONE(1,new int[]{0},"NONE",Type.String,Type.NULL_VALUE,"NONE"),
    ALL(1,new int[]{0},"ALL",Type.String,Type.NULL_VALUE,"ALL"),
    BUSID(1,new int[]{0},"businessId",Type.JSONOBJECT,Type.String,"input"),
    ALLOW(1,new int[]{0},"allowMerchantIds", Type.JSONOBJECT,Type.ARRAY_LONG,new long[]{}),
    DENY(1,new int[]{0},"denyMerchantIds", Type.JSONOBJECT,Type.ARRAY_LONG,new long[]{});

       private int id;
       private int[] parentIds;
       private String key;
       private Type valueType;
       private Type selfType;
       private Object defaultValue;
       private ChBindSC(int id,int[] parentIds,String key,Type selfType,Type valueType,Object defaultValue){
           this.id=id;
           this.parentIds=parentIds;
           this.key=key;
           this.valueType=valueType;
           this.selfType=selfType;
           this.defaultValue=defaultValue;
       }
}
