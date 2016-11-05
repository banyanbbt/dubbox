package com.alibaba.dubbo.rpc.filter.trace.dapper;

import java.io.Serializable;

/**
 * Copyright (C), 2016, 银联智惠信息服务（上海）有限公司
 *
 * @author qianjc
 * @version 0.0.1
 * @desc annotation定义
 * @date 2016-11-04 17:31:55
 */
public class Annotation implements Serializable {

    // 时间戳
    private Long timestamp;
    // annotation类型
    private AnnotationType type;
    // endpoint
    private EndPoint endPoint;
    // 耗时
    private Integer duration;

    public Annotation() {

    }

    public Annotation(Long timestamp, AnnotationType type, EndPoint endPoint, Integer duration) {
        this.timestamp = timestamp;
        this.type = type;
        this.endPoint = endPoint;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Annotation{" +
                "timestamp=" + timestamp +
                ", type=" + type +
                ", endPoint=" + endPoint +
                ", duration=" + duration +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Annotation that = (Annotation) o;

        if (!timestamp.equals(that.timestamp)) return false;
        if (type != that.type) return false;
        if (!endPoint.equals(that.endPoint)) return false;
        return duration.equals(that.duration);

    }

    @Override
    public int hashCode() {
        int result = timestamp.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + endPoint.hashCode();
        result = 31 * result + duration.hashCode();
        return result;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public AnnotationType getType() {
        return type;
    }

    public void setType(AnnotationType type) {
        this.type = type;
    }

    public EndPoint getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(EndPoint endPoint) {
        this.endPoint = endPoint;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
