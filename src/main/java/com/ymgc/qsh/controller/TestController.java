package com.ymgc.qsh.controller;

import com.ymgc.qsh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * simple description
 *
 * @Description: java class description
 * @Author: 张昊
 * @CreateDate: 2019-9-11 11:13
 * @Version: 1.0
 * <p>Copyright: 内蒙古金财信息技术有限公司 (c) 2019</p>
 */
@RestController
public class TestController {

    @Autowired
    private UserService userService;

    @RequestMapping("getUserInfo")
    public String getUserInfo(){
        return userService.getUserInfo().get(0).toString();
    }

}
