package com.alibaba.dubbo.rpc.filter.trace.dapper;

/**
 * Copyright (C), 2016, 银联智惠信息服务（上海）有限公司
 *
 * @author qianjc
 * @version 0.0.1
 * @desc
 * @date 2016-11-04 17:48:46
 */
public class Constants {

    // annotation type相关
    public static final String CS_KEY = "cs";
    public static final String CS_VALUE = "client send";
    public static final String SR_KEY = "sr";
    public static final String SR_VALUE = "server receive";
    public static final String SS_KEY = "ss";
    public static final String SS_VALUE = "server send";
    public static final String CR_KEY = "cr";
    public static final String CR_VALUE = "client receive";

    // RpcInvocation attachment和捕捉annotation中的exception相关
    public static final String TRACE_ID = "traceId";
    public static final String SPAN_ID = "spanId";
    public static final String PARENT_ID = "parentId";
    public static final String SAMPLE = "isSample";
    public static final String EXCEPTION = "exception";
    public static final String DUBBO_EXCEPTION = "dubbo.exception";
    public static final String DUBBO_TIMEPUTEXCEPTION = "dubbo.timeoutexception";
}
