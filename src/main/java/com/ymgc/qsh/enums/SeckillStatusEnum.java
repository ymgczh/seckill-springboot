package com.ymgc.qsh.enums;

/**
 * simple description
 *
 * @Description: java class description
 * @Author: 张昊
 * @CreateDate: 2019-9-11 17:57
 * @Version: 1.0
 * <p>Copyright: 内蒙古金财信息技术有限公司 (c) 2019</p>
 */
public enum  SeckillStatusEnum {
  SUCCESS(1, "秒杀成功"),
  END(0, "秒杀结束"),
  REPEAT(-1, "重复秒杀"),
  INNER_ERROR(-2, "系统异常"),
  DATE_REWRITE(-3, "数据篡改");

    private int status;
    private String statusInfo;

    SeckillStatusEnum(int status, String statusInfo) {
        this.status = status;
        this.statusInfo = statusInfo;
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

    public static SeckillStatusEnum statusOf(int index) {
        for (SeckillStatusEnum status : values()) {
            if (status.getStatus() == index) {
                return status;
            }
        }
        return null;
    }
}
