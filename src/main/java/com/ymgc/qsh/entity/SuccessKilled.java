package com.ymgc.qsh.entity;

import java.util.Date;

public class SuccessKilled {
    private short status;

    private Date createTime;

    private Long seckillId;

    private Long userPhone;

    private Seckill seckill;

    public void setStatus(short status) {
        this.status = status;
    }

    public Seckill getSeckill() {
        return seckill;
    }

    public void setSeckill(Seckill seckill) {
        this.seckill = seckill;
    }

    public Long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(Long seckillId) {
        this.seckillId = seckillId;
    }

    public Long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(Long userPhone) {
        this.userPhone = userPhone;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SuccessKilled{" +
                "status=" + status +
                ", createTime=" + createTime +
                ", seckillId=" + seckillId +
                ", userPhone=" + userPhone +
                '}';
    }
}