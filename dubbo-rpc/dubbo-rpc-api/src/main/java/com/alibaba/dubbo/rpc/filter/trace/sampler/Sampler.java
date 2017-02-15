package com.alibaba.dubbo.rpc.filter.trace.sampler;

/**
 * Copyright (C), 2016, 银联智惠信息服务（上海）有限公司
 *
 * @author qianjc
 * @version 0.0.1
 * @desc 采样率接口
 * @date 2017-02-15 09:46:06
 */
public interface Sampler {

    /**
     * 是否采集
     * @return
     */
    boolean isCollect();
}
