package com.ymgc.qsh.service.impl;

import com.ymgc.qsh.dto.Exposer;
import com.ymgc.qsh.dto.SeckillExecution;
import com.ymgc.qsh.entity.Seckill;
import com.ymgc.qsh.entity.SuccessKilled;
import com.ymgc.qsh.enums.SeckillStatusEnum;
import com.ymgc.qsh.exception.RepeatKillException;
import com.ymgc.qsh.exception.SeckillCloseException;
import com.ymgc.qsh.exception.SeckillException;
import com.ymgc.qsh.mapper.SeckillMapper;
import com.ymgc.qsh.mapper.SuccessKilledMapper;
import com.ymgc.qsh.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * simple description
 *
 * @Description: java class description
 * @Author: 张昊
 * @CreateDate: 2019-9-11 17:27
 * @Version: 1.0
 * <p>Copyright: 内蒙古金财信息技术有限公司 (c) 2019</p>
 */
@Service
public class SeckillServiceImpl implements SeckillService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillMapper seckillMapper;

    @Autowired
    private SuccessKilledMapper successKilledMapper;

    private final String salt = "UIhfdio12381hd9i_)(U*@#JIO1h2u3hneod*&H2n31edhiusn81";

    @Override
    public List<Seckill> getSeckillList() {
        return seckillMapper.queryAll(0,1000);
    }

    @Override
    public Seckill getSeckillById(long seckillId) {
        return seckillMapper.queryById(seckillId);
    }

    @Override
    public Exposer exportSeckillUrl(long seckillId) {
        Seckill seckill = seckillMapper.queryById(seckillId);
        if (seckill == null) {
            return new Exposer(false,seckillId);
        }
        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        Date now = new Date();
        if (now.getTime() < startTime.getTime() || now.getTime() > endTime.getTime()) {
            return new Exposer(false, seckillId,now.getTime(),startTime.getTime(), endTime.getTime());
        }
        //转化特定字符串 不可逆
        String md5 = getMd5(seckillId);
        return new Exposer(true,md5,seckillId);
    }

    @Override
    @Transactional
    /**
     * 使用注解控制事务方法的优点
     *
     */
    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillCloseException, SeckillException, RepeatKillException {
        if (md5 == null || !md5.equals(getMd5(seckillId))) {
            throw new SeckillException("seckill data rewrite");
        }
        //执行秒杀逻辑 减库存 + 记录购买行为
        Date nowTime = new Date();
        try {
            int updateCount = seckillMapper.reduceNumber(seckillId, nowTime);
            if (updateCount <= 0) {
                throw new SeckillCloseException("seckill is closed");
            } else {
                //记录购买行为
                int insertSuccessKilled = successKilledMapper.insertSuccessKilled(seckillId, userPhone);
                if (insertSuccessKilled <= 0) {
                    throw new RepeatKillException("seckill repeat");
                } else {
                    SuccessKilled successKilled = successKilledMapper.queryByIdWithSeckill(seckillId, userPhone);
                    return new SeckillExecution(seckillId, SeckillStatusEnum.SUCCESS, successKilled);
                }
            }
        } catch (SeckillCloseException e) {
            throw e;
        } catch (RepeatKillException e1) {
            throw e1;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new SeckillException("seckill inner error" + e.getMessage());
        }

    }

    private String getMd5(long seckillId) {
        String base = seckillId + "/" + salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }
}
