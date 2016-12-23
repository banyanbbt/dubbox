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
    // 自定义annotation
    private List<BinaryAnnotation> binaryAnnotations;
    // 是否采样
    private Boolean isSample;

    public Span() {

    }

    public Span(Long traceId, Long id, Long parentId, String name, String serviceId, List<Annotation> annotations, List<BinaryAnnotation> binaryAnnotations, Boolean isSample) {
        this.traceId = traceId;
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.serviceId = serviceId;
        this.annotations = annotations;
        this.binaryAnnotations = binaryAnnotations;
        this.isSample = isSample;
    }

    /**
     * 添加annotation
     * @param annotation
     */
    public void addAnnotation(Annotation annotation) {
        if (this.annotations == null) {
            this.annotations = new ArrayList<Annotation>();
        }
        this.annotations.add(annotation);
    }

    /**
     * 添加binaryAnnotation
     * @param binaryAnnotation
     */
    public void addBinaryAnnotation(BinaryAnnotation binaryAnnotation) {
        if (this.binaryAnnotations == null) {
            this.binaryAnnotations = new ArrayList<BinaryAnnotation>();
        }
        this.binaryAnnotations.add(binaryAnnotation);
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
                ", binaryAnnotations=" + binaryAnnotations +
                ", isSample=" + isSample +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Span span = (Span) o;

        if (traceId != null ? !traceId.equals(span.traceId) : span.traceId != null) return false;
        if (id != null ? !id.equals(span.id) : span.id != null) return false;
        if (parentId != null ? !parentId.equals(span.parentId) : span.parentId != null) return false;
        if (name != null ? !name.equals(span.name) : span.name != null) return false;
        if (serviceId != null ? !serviceId.equals(span.serviceId) : span.serviceId != null) return false;
        if (annotations != null ? !annotations.equals(span.annotations) : span.annotations != null) return false;
        if (binaryAnnotations != null ? !binaryAnnotations.equals(span.binaryAnnotations) : span.binaryAnnotations != null)
            return false;
        return isSample != null ? isSample.equals(span.isSample) : span.isSample == null;

    }

    @Override
    public int hashCode() {
        int result = traceId != null ? traceId.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (serviceId != null ? serviceId.hashCode() : 0);
        result = 31 * result + (annotations != null ? annotations.hashCode() : 0);
        result = 31 * result + (binaryAnnotations != null ? binaryAnnotations.hashCode() : 0);
        result = 31 * result + (isSample != null ? isSample.hashCode() : 0);
        return result;
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

    public List<BinaryAnnotation> getBinaryAnnotations() {
        return binaryAnnotations;
    }

    public void setBinaryAnnotations(List<BinaryAnnotation> binaryAnnotations) {
        this.binaryAnnotations = binaryAnnotations;
    }

    public Boolean getSample() {
        return isSample;
    }

    public void setSample(Boolean sample) {
        isSample = sample;
    }
}
