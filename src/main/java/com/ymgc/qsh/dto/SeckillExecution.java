package com.ymgc.qsh.dto;

import com.ymgc.qsh.entity.SuccessKilled;
import com.ymgc.qsh.enums.SeckillStatusEnum;

/**
 * simple description
 *
 * @Description: java class description
 * @Author: 张昊
 * @CreateDate: 2019-9-11 17:15
 * @Version: 1.0
 * <p>Copyright: 内蒙古金财信息技术有限公司 (c) 2019</p>
 */
public class SeckillExecution {

    private long seckillId;

    //秒杀状态
    private int status;

    //秒杀状态表示
    private String statusInfo;

    //秒杀成功信息
    private SuccessKilled successKilled;

    //成功情况下返回
    public SeckillExecution(long seckillId, SeckillStatusEnum seckillStatusEnum, SuccessKilled successKilled) {
        this.seckillId = seckillId;
        this.status = seckillStatusEnum.getStatus();
        this.statusInfo = seckillStatusEnum.getStatusInfo();
        this.successKilled = successKilled;
    }

    //失败情况下
    public SeckillExecution(long seckillId, SeckillStatusEnum seckillStatusEnum) {
        this.seckillId = seckillId;
        this.status = seckillStatusEnum.getStatus();
        this.statusInfo = seckillStatusEnum.getStatusInfo();
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(String statusInfo) {
        this.statusInfo = statusInfo;
    }

    public SuccessKilled getSuccessKilled() {
        return successKilled;
    }

    public void setSuccessKilled(SuccessKilled successKilled) {
        this.successKilled = successKilled;
    }

    @Override
    public String toString() {
        return "SeckillExecution{" +
                "seckillId=" + seckillId +
                ", status=" + status +
                ", statusInfo='" + statusInfo + '\'' +
                ", successKilled=" + successKilled +
                '}';
    }
}
