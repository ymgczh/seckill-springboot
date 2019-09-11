package com.ymgc.qsh.mapper;

import com.ymgc.qsh.entity.Seckill;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface SeckillMapper {

    /**
     * 秒杀
     */
    int reduceNumber(@Param("seckillId") Long seckillId, @Param("killTime") Date killTime);

    Seckill queryById(Long seckillId);

    List<Seckill> queryAll(@Param("offset") int offset,@Param("limit") int limit);
}