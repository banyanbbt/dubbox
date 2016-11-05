package com.alibaba.dubbo.rpc.filter.trace;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.rpc.*;

/**
 * Copyright (C), 2016, 银联智惠信息服务（上海）有限公司
 *
 * @author qianjc
 * @version 0.0.1
 * @desc dubbo rpc trace filter
 * @date 2016-11-03 17:57:54
 */
@Activate(group = {Constants.PROVIDER, Constants.CONSUMER})
public class RpcTraceFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(RpcTraceFilter.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        RpcContext context = RpcContext.getContext();
        boolean isConsumerSide = context.isConsumerSide();



        Result result = invoker.invoke(invocation);
        return result;
    }
}
