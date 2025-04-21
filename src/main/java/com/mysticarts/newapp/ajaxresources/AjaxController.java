package com.mysticarts.newapp.ajaxresources;

import com.mysticarts.newapp.service.AjaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/")
public class AjaxController {


    @Autowired
    private AjaxService ajaxService;
    AjaxController(){
        System.out.println("invoking in the AjaxController...");

    }


    @GetMapping("checkEmail/{email}")
    public  String checkEmailExistence(@PathVariable String  email){

        return ajaxService.checkEmail(email);
    }

    @GetMapping("checkPhone/{phone}")
    public String checkPhoneNum(@PathVariable Long phone){

        return ajaxService.checkPhone(phone);
    }

    @GetMapping("sendOtp/{email}")
public String sendOtp(@PathVariable String email){

    return ajaxService.sendOtp(email);
    }
}
