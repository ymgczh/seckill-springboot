package com.ymgc.qsh.service;

import com.ymgc.qsh.QshApplication;
import com.ymgc.qsh.dto.Exposer;
import com.ymgc.qsh.dto.SeckillExecution;
import com.ymgc.qsh.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by Administrator on 2019/9/11 0011.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {QshApplication.class})
public class SeckillServiceTest {
    @Autowired
    private SeckillService seckillService;

    @Test
    public void getSeckillList() throws Exception {
        List<Seckill> seckillList = seckillService.getSeckillList();
        System.out.println(seckillList);
    }

    @Test
    public void getSeckillById() throws Exception {
        Seckill seckill = seckillService.getSeckillById(1000l);
        System.out.println(seckill);
    }

    @Test
    public void exportSeckillUrl() throws Exception {

        long seckillId = 1001l;
        Exposer exposer = seckillService.exportSeckillUrl(seckillId);
        System.out.println(exposer);

        /*
        Exposer{exposed=false, md5='null', seckillId=1000, now=1568205595647, start=1546300800000, end=1556841599000}
        Exposer{exposed=true, md5='8f316070934992d8a6398e7f9fc04eb9', seckillId=1000, now=0, start=0, end=0}
         */
    }

    @Test
    public void executeSeckill() throws Exception {
        long seckillId = 1000l;
        long phone = 12755667788l;
        String md5 = "8f316070934992d8a6398e7f9fc04eb9";
        SeckillExecution seckillExecution = seckillService.executeSeckill(seckillId, phone, md5);
        System.out.println(seckillExecution);
        /*
        SeckillExecution{seckillId=1000, status=1, statusInfo='秒杀成功',
        successKilled=SuccessKilled{status=0, createTime=Thu Sep 12 04:48:01 CST 2019, seckillId=1000,
         userPhone=17755667788, seckill=Seckill{seckillId=1000, name='null', number=95,
        startTime=Tue Jan 01 08:00:00 CST 2019, endTime=Tue Dec 03 07:59:59 CST 2019, createTime=Sun Jan 13 04:48:03 CST 2019}}}
         */
    }

}

