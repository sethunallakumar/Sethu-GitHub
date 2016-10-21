package com.sella.chatbots.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sella.chatbots.bo.RequestBO;
import com.sella.chatbots.bo.ResponseBO;
import com.sella.chatbots.utils.ChatUtils;



@Controller
@RequestMapping("/ws")
class IndexController
{

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping(value="/processtext",produces="application/json",method={RequestMethod.POST})
    public @ResponseBody ResponseBO  displayRequestPage(final @RequestBody RequestBO requestmsg)
    {
        return new ChatUtils().PROCESSMSG(requestmsg);

    }
    
 
 

}
