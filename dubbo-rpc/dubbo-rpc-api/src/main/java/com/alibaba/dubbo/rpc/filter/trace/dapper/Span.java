package com.alibaba.dubbo.rpc.filter.trace.dapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2016, 银联智惠信息服务（上海）有限公司
 *
 * @author qianjc
 * @version 0.0.1
 * @desc span定义
 * @date 2016-11-04 09:39:40
 */
public class Span implements Serializable {

    // 跟踪号
    private Long traceId;
    // span的id
    private Long id;
    // 该span的父id，如果为null则该span为父span
    private Long parentId;
    // span的名字
    private String name;
    // serviceId
    private String serviceId;
    // annotations
    private List<Annotation> annotations;
    // 是否采样
    private boolean isSample;

    public Span() {

    }

    public Span(Long traceId, Long id, Long parentId, String name, String serviceId, List<Annotation> annotations, boolean isSample) {
        this.traceId = traceId;
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.serviceId = serviceId;
        this.annotations = annotations;
        this.isSample = isSample;
    }

    @Override
    public String toString() {
        return "Span{" +
                "traceId=" + traceId +
                ", id=" + id +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                ", serviceId='" + serviceId + '\'' +
                ", annotations=" + annotations +
                ", isSample=" + isSample +
                '}';
    }

    public void addAnnotation(Annotation annotation) {
        if (this.annotations == null) {
            this.annotations = new ArrayList<Annotation>();
        }
        this.annotations.add(annotation);
    }

    public Long getTraceId() {
        return traceId;
    }

    public void setTraceId(Long traceId) {
        this.traceId = traceId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public List<Annotation> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<Annotation> annotations) {
        this.annotations = annotations;
    }

    public boolean isSample() {
        return isSample;
    }

    public void setSample(boolean sample) {
        isSample = sample;
    }
}
