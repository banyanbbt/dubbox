package com.alibaba.dubbo.rpc.filter.trace;

import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.rpc.*;
import com.alibaba.dubbo.rpc.filter.trace.dapper.AttachmentUtil;
import com.alibaba.dubbo.rpc.filter.trace.dapper.Constants;
import com.alibaba.dubbo.rpc.filter.trace.dapper.EndPoint;
import com.alibaba.dubbo.rpc.filter.trace.dapper.Span;

/**
 * Copyright (C), 2016, 银联智惠信息服务（上海）有限公司
 *
 * @author qianjc
 * @version 0.0.1
 * @desc dubbo rpc trace filter
 * @date 2016-11-03 17:57:54
 */
@Activate(group = {com.alibaba.dubbo.common.Constants.PROVIDER, com.alibaba.dubbo.common.Constants.CONSUMER})
public class RpcTraceFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(RpcTraceFilter.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        RpcContext context = RpcContext.getContext();
        boolean isConsumerSide = context.isConsumerSide();
        boolean isProviderSide = context.isProviderSide();
        String methodName = context.getMethodName();
        RpcInvocation rpcInvocation = (RpcInvocation) invocation;

        Tracer tracer = Tracer.getInstance();

        // TODO: 设计serviceName
        EndPoint endPoint = tracer.buildEndPoint(context.getLocalAddressString(), context.getLocalPort(), "");

        Span span = null;
        if (isConsumerSide) {
            // 如果是消费者
            LOGGER.info("消费者");
            Span parentSpan = tracer.getParentSpan();
            if (null == parentSpan) {
                // 如果parentSpan为null, 表示该Span为root span
                // TODO: 设计serviceId
                span = tracer.newSpan(methodName, "");
            } else {
                // 叶子span
                // TODO: 设计serviceId
                span = tracer.buildSpan(parentSpan.getTraceId(), parentSpan.getId(), tracer.generateSpanId(), methodName, parentSpan.isSample(), "");
            }
        } else if (isProviderSide) {
            // 如果是生产者
            LOGGER.info("生产者");
            Long traceId = AttachmentUtil.getAttachmentLong(rpcInvocation, Constants.TRACE_ID);
            Long parentId = AttachmentUtil.getAttachmentLong(rpcInvocation, Constants.PARENT_ID);
            Long spanId = AttachmentUtil.getAttachmentLong(rpcInvocation, Constants.SPAN_ID);
            boolean isSample = traceId != null;
            // TODO: 设计serviceId
            span = tracer.buildSpan(traceId, parentId, spanId, methodName, isSample, "");
        }

        LOGGER.info("我是Span" + span.toString());

        this.setAttachment(rpcInvocation, span);

        Result result = invoker.invoke(invocation);
        return result;
    }

    /**
     * 将Span的相关值设置到RpcInvocation的attachment中, 然后传递到下游
     * @param invocation
     * @param span
     */
    private void setAttachment(RpcInvocation invocation, Span span) {
        if (span.isSample() && null != span) {
            // 如果进行采样
            invocation.setAttachment(Constants.TRACE_ID, String.valueOf(span.getTraceId()));
            invocation.setAttachment(Constants.SPAN_ID, String.valueOf(span.getId()));
            invocation.setAttachment(Constants.PARENT_ID, String.valueOf(span.getParentId()));
            invocation.setAttachment(Constants.SAMPLE, String.valueOf(span.isSample()));
        }

    }
}
