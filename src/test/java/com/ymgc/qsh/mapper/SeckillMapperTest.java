package com.ymgc.qsh.mapper;

import com.ymgc.qsh.QshApplication;
import com.ymgc.qsh.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * simple description
 *
 * @Description: java class description
 * @Author: 张昊
 * @CreateDate: 2019-9-11 16:20
 * @Version: 1.0
 * <p>Copyright: 内蒙古金财信息技术有限公司 (c) 2019</p>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {QshApplication.class})
public class SeckillMapperTest {

  @Autowired
  private SeckillMapper seckillMapper;

  @Test
  public void reduceNumber() {
    int reduceNum = seckillMapper.reduceNumber(1000l,new Date());
    System.out.println(reduceNum);
  }

  @Test
  public void queryById() {
    Seckill seckill = seckillMapper.queryById(1000l);
    System.out.println(seckill);
  }

  @Test
  public void queryAll() {
    List<Seckill> seckills = seckillMapper.queryAll(0, 100);
    for (Seckill seckill : seckills) {
      System.out.println(seckill);
    }
  }
}