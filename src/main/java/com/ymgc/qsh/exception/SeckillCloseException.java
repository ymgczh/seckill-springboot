package com.ymgc.qsh.exception;

/**
 * simple description
 *
 * @Description: java class description
 * @Author: 张昊
 * @CreateDate: 2019-9-11 17:19
 * @Version: 1.0
 * <p>Copyright: 内蒙古金财信息技术有限公司 (c) 2019</p>
 */
public class SeckillCloseException extends SeckillException {

    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
