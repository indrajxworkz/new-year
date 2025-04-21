package com.mysticarts.newapp.service;

import com.mysticarts.newapp.entity.ProjectEntity;
import com.mysticarts.newapp.repo.ProjectRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
public class AjaxServiceImpl implements AjaxService {

    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    private MailSenderService senderService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public String checkEmail(String email) {

       boolean exists =  projectRepo.checkEmail(email);

       log.info("checking the email through javax :   {} ",email);
       if (exists){
           return "Email Exists";
       }
        return "Email doesn't Exists";
    }

    @Override
    public String checkPhone(Long phone) {

        boolean phoneNum = projectRepo.checkPhone(phone);
        if (phoneNum){
            return "Phone number Exists";
        }
        return "Phone Number Doesn't Exists";
    }

    @Override
    public String sendOtp(String email) {
        ProjectEntity entity = projectRepo.getEntityByEmail(email);
        boolean projectEntity= projectRepo.checkEmail(email);
        log.info("is project entity  : {} ",projectEntity);
        Random random   = new Random();
        String otp= String.valueOf(random.nextInt(999999));
        if (projectEntity ){
            senderService.sendRegistrationEmail("The Otp to reset  " ,otp ,email );
            entity.setOtp(passwordEncoder.encode(otp));
            projectRepo.updateEntityDetails(entity);
            return "OTP Sent successful.";

        }

     return  "email Invalid";

    }
}
