package com.mysticarts.newapp.service;

import com.mysticarts.newapp.dto.ProjectDto;
import org.springframework.web.multipart.MultipartFile;

public interface ProjectService {

    public boolean validateAndSave(ProjectDto projectDto);

    ProjectDto getEntityByEmail(String email);

    ProjectDto getEntityById(Integer id);

    boolean updatePasswordByEmail(String email, String pwd, String confirmPwd);

    boolean updateDetailsByEmail(ProjectDto projectDto);

    ProjectDto updateById(ProjectDto projectDto , ProjectDto sessionDto, MultipartFile multipartFile);

    ProjectDto validateAndSignIn(String  email, String pwd);

    boolean validateOtp(String otp, String email);


}
