package com.alibaba.dubbo.rpc.filter.trace.dapper;

/**
 * Copyright (C), 2016, 银联智惠信息服务（上海）有限公司
 *
 * @author qianjc
 * @version 0.0.1
 * @desc annotation支持的类型
 * @date 2016-11-04 17:44:40
 */
public enum AnnotationType {

    CS(Constants.CS_KEY, Constants.CS_VALUE),
    SR(Constants.SR_KEY, Constants.SR_VALUE),
    SS(Constants.SS_KEY, Constants.SS_VALUE),
    CR(Constants.CR_KEY, Constants.CR_VALUE);

    private String symbol;

    private String label;

    private AnnotationType(String symbol, String label) {
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
