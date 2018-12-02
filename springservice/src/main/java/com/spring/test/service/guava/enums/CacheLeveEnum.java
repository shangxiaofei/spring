package com.spring.test.service.guava.enums;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午7:40 2018/6/7
 */
public enum  CacheLeveEnum {
    GUAVA(1),
    DB(3);
    private int leve;
    private CacheLeveEnum(int leve){
        this.leve=leve;
    }
    public CacheLeveEnum valueOfCacheLeveEnum(int leve){
        CacheLeveEnum[] cacheLeveEnums= CacheLeveEnum.values();
        for(int i=0;i<cacheLeveEnums.length;i++){
            CacheLeveEnum cacheLeveEnum=cacheLeveEnums[i];
            if(cacheLeveEnum.leve==i){
                return cacheLeveEnum;
            }
        }
        throw  new RuntimeException("错误的缓存级别");
    }
}
