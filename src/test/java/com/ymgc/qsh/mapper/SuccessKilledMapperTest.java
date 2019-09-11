package com.ymgc.qsh.mapper;

import com.ymgc.qsh.QshApplication;
import com.ymgc.qsh.entity.SuccessKilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * simple description @Description: java class description @Author: 张昊 @CreateDate: 2019-9-11
 * 16:49 @Version: 1.0
 *
 * <p>Copyright: 内蒙古金财信息技术有限公司 (c) 2019
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {QshApplication.class})
public class SuccessKilledMapperTest {

  @Autowired private SuccessKilledMapper successKilledMapper;

  @Test
  public void insertSuccessKilled() {
    Long id = 1000l;
    Long phone = 18647961475l;
    int result = successKilledMapper.insertSuccessKilled(id, phone);
    System.out.println(result);
  }

  @Test
  public void queryByIdWithSeckill() {
    SuccessKilled successKilled = successKilledMapper.queryByIdWithSeckill(1000l,18647961475l);
    System.out.println(successKilled);
  }
}
