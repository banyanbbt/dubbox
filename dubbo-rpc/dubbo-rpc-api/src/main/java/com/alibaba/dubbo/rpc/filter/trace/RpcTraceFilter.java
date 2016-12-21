package com.alibaba.dubbo.rpc.filter.trace;

import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.rpc.*;
import com.alibaba.dubbo.rpc.filter.trace.dapper.AttachmentUtil;
import com.alibaba.dubbo.rpc.filter.trace.dapper.Constants;
import com.alibaba.dubbo.rpc.filter.trace.dapper.EndPoint;
import com.alibaba.dubbo.rpc.filter.trace.dapper.Span;
import com.alibaba.dubbo.remoting.TimeoutException;

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
        try {
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

            // 调用具体业务逻辑之前处理
            this.invokeBefore(span, isConsumerSide, isProviderSide);
            // 传递附件到RpcInvocation, 传递到下游，确保下游能够收到traceId等相关信息，保证请求能够标记到正确的traceId
            this.setAttachment(rpcInvocation, span);
            // 执行具体的业务或者下游的filter
            Result result = invoker.invoke(invocation);

            if (result.hasException()) {
                // 如果在请求过程中发生了异常, 需要进行异常的处理和相关annotation的记录
                this.processException();
            }

            return result;
        } catch (RpcException e) {
            if (null != e.getCause() && e.getCause() instanceof TimeoutException) {
                // 如果异常是com.alibaba.dubbo.remoting.TimeoutException
                this.processTimeoutException();
            } else {
                // 其他异常
                this.processException();
            }
            // 将异常抛出去
            throw e;
        } finally {
            // 调用具体业务逻辑之后处理
            if (null != span) {
                this.invokeAfter(span, isConsumerSide, isProviderSide);
            }
        }

    }

    private void processTimeoutException() {

    }

    private void processException() {

    }

    /**
     * 将Span的相关值设置到RpcInvocation的attachment中, 然后传递到下游
     * @param invocation
     * @param span
     */
    private void setAttachment(RpcInvocation invocation, Span span) {
        if (span.isSample() && null != span) {
            // 如果进行采样
            invocation.setAttachment(Constants.TRACE_ID, span.getTraceId() == null ? null : String.valueOf(span.getTraceId()));
            invocation.setAttachment(Constants.SPAN_ID, span.getId() == null ? null : String.valueOf(span.getId()));
            invocation.setAttachment(Constants.PARENT_ID, span.getParentId() == null ? null : String.valueOf(span.getParentId()));
            invocation.setAttachment(Constants.SAMPLE, span.isSample() == null ? null : String.valueOf(span.isSample()));
        }

    }

    /**
     * 调用具体逻辑之前，记录相关的annotation、设置对应的parentSpan
     * @param span
     * @param isConsumerSide
     * @param isProviderSide
     */
    private void invokeBefore(Span span, boolean isConsumerSide, boolean isProviderSide) {
        Tracer tracer = Tracer.getInstance();
        if (isConsumerSide && span.isSample()) {
            // 如果是消费者, ClientSend

        } else if (isProviderSide) {
            // 如果是提供者
            if (span.isSample()) {
                // ServerReceive
            }
            // 将该span作为parentSpan设置到ThreadLocal中
            tracer.setParentSpan(span);
        }
    }

    /**
     * 调用具体逻辑之后，记录相关的annotation、去除对应的parentSpan
     * @param span
     * @param isConsumerSide
     * @param isProviderSide
     */
    private void invokeAfter(Span span, boolean isConsumerSide, boolean isProviderSide) {
        Tracer tracer = Tracer.getInstance();
        if (isConsumerSide && span.isSample()) {
            // 如果是消费者, ClientReceive
        } else if (isProviderSide) {
            // 如果是提供者
            if (span.isSample()) {
                // ServerSend
            }
            tracer.removeParentSpan();
        }
    }
}
