package com.alibaba.dubbo.rpc.filter.trace.sampler;

/**
 * Copyright (C), 2016, 银联智惠信息服务（上海）有限公司
 *
 * @author qianjc
 * @version 0.0.1
 * @desc 基于hash进行采样设计
 * @date 2017-02-15 11:07:38
 */
public class HashSampler implements Sampler {

    @Override
    public boolean isCollect() {
        // TODO: 具体实现
        return false;
    }
}
