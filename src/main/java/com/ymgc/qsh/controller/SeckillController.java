package com.ymgc.qsh.controller;

import com.ymgc.qsh.dto.Exposer;
import com.ymgc.qsh.dto.SeckillExecution;
import com.ymgc.qsh.dto.SeckillResult;
import com.ymgc.qsh.entity.Seckill;
import com.ymgc.qsh.enums.SeckillStatusEnum;
import com.ymgc.qsh.exception.RepeatKillException;
import com.ymgc.qsh.exception.SeckillCloseException;
import com.ymgc.qsh.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/seckill")
public class SeckillController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(Model model) {
        List<Seckill> seckillList = seckillService.getSeckillList();
        model.addAttribute("list",seckillList);
        return "list";
    }

    @RequestMapping(value = "/{seckillId}/detail",method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") Long seckillId, Model model) {
        if (seckillId == null){
            return "redirect:/seckill/list";
        }
        Seckill seckill = seckillService.getSeckillById(seckillId);
        if (seckill == null) {
            return "forward:/seckill/list";
        }
        model.addAttribute("seckill",seckill);
        return "detail";
    }

    @RequestMapping(value = "/{seckillId}/exposer",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"}
    )
    @ResponseBody
    public SeckillResult<Exposer> exposer(@PathVariable("seckillId") Long seckillId) {
        SeckillResult<Exposer> result;
        try{
            Exposer exposer = seckillService.exportSeckillUrl(seckillId);
            result = new SeckillResult<Exposer>(true,exposer);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            result = new SeckillResult<Exposer>(false,e.getMessage());
        }
        return result;
    }

    @RequestMapping(
            value = "/{seckillId}/{md5}/execution",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"}
    )
    @ResponseBody
    public SeckillResult<SeckillExecution> execute(@PathVariable("seckillId") Long seckillId,
                                                   @PathVariable("md5") String md5,
                                                   @CookieValue(value = "killPhone", required = false) Long phone) {
        if (phone == null) {
            return new SeckillResult<SeckillExecution>(false,"未注册");
        }
        SeckillResult<SeckillExecution> result;
        try{
            SeckillExecution seckillExcution = seckillService.executeSeckill(seckillId, phone, md5);
            return new SeckillResult<SeckillExecution>(true,seckillExcution);
        } catch (RepeatKillException re) {
            SeckillExecution excution = new SeckillExecution(seckillId, SeckillStatusEnum.REPEAT);
            return new SeckillResult<SeckillExecution>(false,excution);
        } catch (SeckillCloseException ce) {
            SeckillExecution excution = new SeckillExecution(seckillId, SeckillStatusEnum.END);
            return new SeckillResult<SeckillExecution>(false,excution);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            SeckillExecution excution = new SeckillExecution(seckillId, SeckillStatusEnum.INNER_ERROR);
            return new SeckillResult<SeckillExecution>(false,excution);
        }
    }

    @RequestMapping(value = "/time/now", method = RequestMethod.GET)
    @ResponseBody
    public SeckillResult<Long> time() {
        Date now = new Date();
        return new SeckillResult(true,now.getTime());
    }
}
