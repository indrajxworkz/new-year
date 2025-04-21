package com.mysticarts.newapp.controller;

import com.mysticarts.newapp.dto.ProjectDto;
import com.mysticarts.newapp.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@RequestMapping
public class SpringController {

    @Autowired
    ProjectService service;

    @Autowired
    PasswordEncoder passwordEncoder;

    SpringController(){
        System.out.println("invoking the SpringController..");
    }


    @PostMapping("signup")
    public String onRegister(@Valid ProjectDto projectDto, BindingResult result, Model model) {
        log.info("onSave method Invoked : ");
        boolean hasErrors = result.hasErrors();
        if (hasErrors) {
            List<ObjectError> objectErrors = result.getAllErrors();
            objectErrors.forEach(objectError -> {
                FieldError fieldError = (FieldError) objectError;
                System.out.println(fieldError.getField() + " ErrorMessages :  " + fieldError.getDefaultMessage());
                if (fieldError.getField().equalsIgnoreCase("firstName")) {
                    model.addAttribute("firstNameError", fieldError.getDefaultMessage());
                }
                if (fieldError.getField().equalsIgnoreCase("lastName")) {
                    model.addAttribute("lastName", fieldError.getDefaultMessage());
                }
                if (fieldError.getField().equalsIgnoreCase("userName")) {
                    model.addAttribute("userName", fieldError.getDefaultMessage());
                }
            });
            model.addAttribute("projectDto", projectDto);
            return "signUp";
        }
        boolean regi = service.validateAndSave(projectDto);
        model.addAttribute("register", projectDto);
        service.validateAndSave(projectDto);
        System.out.println("New User has been registered");
        model.addAttribute("firstName", projectDto.getFirstName());
        if (regi) {
            return "response";
        } else {
            System.out.println("email already exists");
            return "signUp";
        }

    }

    @GetMapping("index")
    public String getIndex() {
        return "index";
    }

    @GetMapping("signUp")
    public String getSignUp() {
        return "signUp";
    }

    @PostMapping ("signin")
    public String signIn(@RequestParam String email, @RequestParam String pwd, Model model) {
      log.info("the email is  :  {}",email);
      log.info("the password is :{}",pwd);
        ProjectDto dto = service.validateAndSignIn(email,pwd);;
        log.info("{} you are logging in  login method ",email);
        if (dto!=null&&dto.getEmail()!=null&&dto.getEmail().equalsIgnoreCase(email)) {
            log.info("{} signed in successfully :   " ,email);
            model.addAttribute("dto", dto);
            return "dashboard";
        } else {
            log.info(" {} invalid creds  :   " , email);
            model.addAttribute("valid", "Email or password invalid");
            model.addAttribute("found",email);
            if (dto.getAttempt() >= 3){
                model.addAttribute("valid","Account is blocked Please reset the Password to login");

            }
            return "signIn";
        }

    }

    @GetMapping("signIn")
    public String getSighIn() {
        return "signIn";
    }

    @PostMapping("resetPassword")
    public String updatePassword(String email, String pwd, String confirmPwd, Model model) {

        log.info("updating password method is invoked : {}" , email);
        log.trace("{0} ,checking the method update is  invoked or not ");

        boolean isUpdated = service.updatePasswordByEmail(email, pwd, confirmPwd);
        if (isUpdated) {
            model.addAttribute("FoundEmail",email);
            model.addAttribute("FoundEmail", "Password Updated Successfully");

            return "resetPassword";
        } else {

            model.addAttribute("FoundEmail", "Password Doesn't match");
            return "resetPassword";
        }
    }

    @GetMapping("reset")
    public String getResetPassword() {
        return "resetPassword";
    }

    @GetMapping("fetch")
    public String fetchDetails(@RequestParam String email, Model model, HttpSession session) {
        log.info("email logged in : {}" , email);
        log.trace(" checking that if Fetch method is invoked  ");

        ProjectDto projectDto= service.getEntityByEmail(email);
        log.info("{}",projectDto);
        session.setAttribute("detailsUpdate", projectDto);
        model.addAttribute("detailsUpdate", projectDto);
        return "update";
    }


    @PostMapping("update")
    public String update(ProjectDto projectDto, BindingResult result, Model model, HttpServletRequest request, @RequestParam("file")MultipartFile multipartFile) {
        log.info(" update method is invoked  :  {}  ", projectDto);
        ProjectDto
                sessionDto = (ProjectDto) request.getSession().getAttribute("detailsUpdate");
        log.info("  this is the dto   :  {}" , projectDto);
        ProjectDto updatedDto = service.updateById(projectDto, sessionDto,multipartFile);
          if (result.hasErrors()) {
            log.info("getting errors: {}"," These are the errors generated");
            result.getAllErrors().forEach(objectError -> {
                        FieldError fieldError = (FieldError) objectError;
                        model.addAttribute(fieldError.getField() + "Error", fieldError.getDefaultMessage());

                    }
            );

            model.addAttribute("firstName", projectDto.getFirstName());
            model.addAttribute("detailsUpdate",updatedDto);
            
        }

        return "update";
    }

   @GetMapping("fetch/signIn")
    public  String goToSignInPage(){
        return "signIn";
   }

   @GetMapping("fetch/index")
    public  String goToHomePage(){
        return "index";
   }

   @GetMapping("fetch/resetPassword")
    public String gotoResetPage(){
        return "resetPassword";
   }

   @GetMapping("dashboard")
    public String goToDashBoard(@RequestParam String  email, Model model,ProjectDto projectDto){

        ProjectDto dto = service.getEntityByEmail(email);
        if (email.equals(projectDto.getEmail())){
            model.addAttribute("dto",dto);
            return "dashboard";

        }
      log.info("{}:  this is returning dashboard",dto);
        return "dashboard";
   }
   @GetMapping("forgot-password")
   public String handleForgotPassword(){
        return "resetOtp";
   }

@PostMapping("resetOtp")
    public String validateTheOtp(@RequestParam String otp ,@RequestParam String email,Model model){
        boolean isOtpValid = service.validateOtp(otp,email);
        if (isOtpValid){
            model.addAttribute("email","Email is being verified");

            return "resetPassword";
        }else{
         model.addAttribute("email","Please enter the valid Otp");
        }
        return "resetOtp";
}
}


