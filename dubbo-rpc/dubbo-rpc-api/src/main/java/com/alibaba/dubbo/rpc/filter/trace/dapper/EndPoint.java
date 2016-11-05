package com.alibaba.dubbo.rpc.filter.trace.dapper;

import java.io.Serializable;

/**
 * Copyright (C), 2016, 银联智惠信息服务（上海）有限公司
 *
 * @author qianjc
 * @version 0.0.1
 * @desc endpoint定义
 * @date 2016-11-04 17:32:03
 */
public class EndPoint implements Serializable {

    // rpc服务启动的ip
    private String ip;
    // rpc服务启动的端口
    private String port;
    // rpc服务名字
    private String serviceName;

    public EndPoint() {

    }

    public EndPoint(String ip, String port, String serviceName) {
        this.ip = ip;
        this.port = port;
        this.serviceName = serviceName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EndPoint endPoint = (EndPoint) o;

        if (!ip.equals(endPoint.ip)) return false;
        if (!port.equals(endPoint.port)) return false;
        return serviceName.equals(endPoint.serviceName);

    }

    @Override
    public int hashCode() {
        int result = ip.hashCode();
        result = 31 * result + port.hashCode();
        result = 31 * result + serviceName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "EndPoint{" +
                "ip='" + ip + '\'' +
                ", port='" + port + '\'' +
                ", serviceName='" + serviceName + '\'' +
                '}';
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
