package com.ymgc.qsh.service;

import com.ymgc.qsh.dto.Exposer;
import com.ymgc.qsh.dto.SeckillExecution;
import com.ymgc.qsh.entity.Seckill;
import com.ymgc.qsh.exception.RepeatKillException;
import com.ymgc.qsh.exception.SeckillCloseException;
import com.ymgc.qsh.exception.SeckillException;

import java.util.List;

/**
 * 站在使用者角度写接口
 * 粒度-参数-返回类型 三个方面注意
 *
 * @Description: java class description
 * @Author: 张昊
 * @CreateDate: 2019-9-11 17:02
 * @Version: 1.0
 * <p>Copyright: 内蒙古金财信息技术有限公司 (c) 2019</p>
 */
public interface SeckillService {

    List<Seckill> getSeckillList();

    Seckill getSeckillById(long seckillId);

    /**
     * 输出秒杀地址
     * 否则输出系统时间和秒杀时间
     */
    Exposer exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀操作
     * @param seckillId
     * @param userPhone
     * @param md5
     */
    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws
            SeckillCloseException, SeckillException, RepeatKillException;
}
