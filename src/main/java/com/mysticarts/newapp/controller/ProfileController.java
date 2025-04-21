package com.mysticarts.newapp.controller;

import com.mysticarts.newapp.dto.ServiceConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Controller
@RequestMapping("/")
public class ProfileController {

    ProfileController(){
        System.out.println("invoking in the ProfileController");
    }


    @GetMapping("view/{imageName}")

    public void profileHandler(@PathVariable String imageName , HttpServletResponse response, Model model) throws IOException {

        Path path   = Paths.get(ServiceConstant.FILE_PATH.getPath()+imageName);
        log.info("{}  " ,path);
        model.addAttribute("path",path);
        Files.copy(path,response.getOutputStream());
        response.getOutputStream().flush();

    }




}
