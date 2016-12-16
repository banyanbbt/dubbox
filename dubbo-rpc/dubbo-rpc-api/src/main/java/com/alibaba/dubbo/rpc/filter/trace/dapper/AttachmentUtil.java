package com.alibaba.dubbo.rpc.filter.trace.dapper;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.rpc.RpcInvocation;

/**
 * Copyright (C), 2016, 银联智惠信息服务（上海）有限公司
 *
 * @author qianjc
 * @version 0.0.1
 * @desc dubbo RpcInvocation的Attachment操作, 能够向下游传递参数
 * @date 2016-12-16 15:55:48
 */
public class AttachmentUtil {

    /**
     * 将RpcInvocation的attachment数据转换成Long
     * @param invocation
     * @param key
     * @return
     */
    public static Long getAttachmentLong(RpcInvocation invocation, String key) {
        String value = invocation.getAttachment(key);
        if (StringUtils.isBlank(value)) {
            return null;
        }
        return Long.valueOf(value);
    }

    /**
     * 将RpcInvocation的attachment数据转换成Boolean
     * @param invocation
     * @param key
     * @return
     */
    public static Boolean getAttachmentBoolean(RpcInvocation invocation, String key) {
        String value = invocation.getAttachment(key);
        if (StringUtils.isBlank(value)) {
            return false;
        }
        return Boolean.valueOf(value);
    }
}
