package com.ymgc.qsh.mapper;

import com.ymgc.qsh.entity.Seckill;

import java.util.Date;
import java.util.List;

public interface SeckillMapper {

    /**
     * 秒杀
     */
    int reduceNumber(Long seckillId, Date killTime);

    Seckill queryById(Long seckillId);

    List<Seckill> queryAll(int offset, int limit);
}