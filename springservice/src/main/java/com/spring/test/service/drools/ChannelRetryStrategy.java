package com.spring.test.service.drools;

import java.util.List;
import java.util.Map;

/**
 * 平台响应码重试策略
 */
public class ChannelRetryStrategy {
    /**
     * 平台码id
     */
    private String errorCodeId;
    /**
     * 平台Code
     */
    private String errorCode;
    /**
     * 是否可重试
     */
    private boolean isRetry;
    /**
     * 可重试次数
     */
    private int retryCount;
}


class Test{

    //通道id->重试策略
    Map<Integer,List<ChannelRetryStrategy>> channelIdForStrategy;
}
