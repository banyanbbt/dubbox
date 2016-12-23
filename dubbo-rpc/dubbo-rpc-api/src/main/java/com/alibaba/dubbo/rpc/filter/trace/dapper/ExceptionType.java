package com.alibaba.dubbo.rpc.filter.trace.dapper;

/**
 * Copyright (C), 2016, 银联智惠信息服务（上海）有限公司
 *
 * @author qianjc
 * @version 0.0.1
 * @desc binary annotation的一种，异常类型
 * @date 2016-12-21 15:13:28
 */
public enum ExceptionType {

    EXCEPTION(Constants.DUBBO_EXCEPTION, Constants.EXCEPTION),
    TIMEOUTEXCEPTION(Constants.DUBBO_TIMEPUTEXCEPTION, Constants.EXCEPTION);

    private String symbol;

    private String label;

    private ExceptionType(String symbol, String label) {
        this.symbol = symbol;
        this.label = label;
    }

    public String symbol() {
        return this.symbol;
    }

    public String label() {
        return this.label;
    }
}
