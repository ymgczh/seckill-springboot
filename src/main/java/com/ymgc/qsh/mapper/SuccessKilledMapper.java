package com.ymgc.qsh.mapper;

import com.ymgc.qsh.entity.SuccessKilled;
import org.apache.ibatis.annotations.Param;

public interface SuccessKilledMapper {

    /**
     * 插入购买明细且过滤重复
     * @param
     * @return
     * @date     2019-9-11 15:10
     * @version  1.0
     */
    int insertSuccessKilled(@Param("seckillId") Long seckillId, @Param("userPhone") Long userPhone);

    /**
     * 携带秒杀对象查询
     * @param
     * @return
     * @date     2019-9-11 15:12
     * @version  1.0
     */
    SuccessKilled queryByIdWithSeckill(@Param("seckillId") Long seckillId, @Param("userPhone") Long userPhone);
}