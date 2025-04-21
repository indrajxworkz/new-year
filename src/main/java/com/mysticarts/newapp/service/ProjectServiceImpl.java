package com.mysticarts.newapp.service;

import com.mysticarts.newapp.dto.MessageConstant;
import com.mysticarts.newapp.dto.ProjectDto;
import com.mysticarts.newapp.dto.ServiceConstant;
import com.mysticarts.newapp.entity.ProjectEntity;
import com.mysticarts.newapp.repo.ProjectRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

@Slf4j
@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    ProjectRepo projectRepo;

    @Autowired
    MailSenderService senderService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public boolean validateAndSave(ProjectDto projectDto) {
        boolean isRegistered = false;
        ProjectEntity projectEntity = new ProjectEntity();
        projectDto.setPwd(passwordEncoder.encode(projectDto.getPwd()));
        if (projectDto.getFirstName() != null && !projectDto.getFirstName().isEmpty()) {
            if (projectDto.getUserName() != null && !projectDto.getUserName().isEmpty()) {
                BeanUtils.copyProperties(projectDto, projectEntity);
                projectEntity.setCreatedBy(projectEntity.getFirstName() + " " + projectEntity.getUserName());
                projectEntity.setCreatedDate(LocalDate.now());
                projectEntity.setFileName("blank.jpg");
                projectEntity.setFileType("jpg/png");

                 isRegistered = projectRepo.save(projectEntity);
                    if (isRegistered){

                        senderService.sendRegistrationEmail("Registration Confirmation","Thank you for Registration ",projectDto.getEmail());
                        System.out.println("User Registration Done !! ");
                    }else {
                        log.info("{}the email from the entity already exits :  " ,projectEntity.getEmail());
                    }
            }
        }

        return isRegistered;
    }

    @Override
    public ProjectDto getEntityByEmail(String email) {
        log.info(" this is the email to fetch the details{}", email);
        ProjectDto projectDto = new ProjectDto();
        ProjectEntity entity = projectRepo.getEntityByEmail(email);
        BeanUtils.copyProperties(entity,projectDto);
        return projectDto;
    }

    @Override
    public ProjectDto getEntityById(Integer id) {
        log.info(" this is the id  to fetch the details{}", id);
        ProjectDto projectDto = new ProjectDto();
        ProjectEntity entity = projectRepo.getEntityById(id);
        BeanUtils.copyProperties(entity,projectDto);
        return projectDto;

    }

    @Override
    public boolean updatePasswordByEmail(String email, String pwd, String confirmPwd) {

        return projectRepo.updatePasswordByEmail(email, passwordEncoder.encode(pwd), confirmPwd);
    }

    @Override
    public boolean updateDetailsByEmail(ProjectDto projectDto) {

            ProjectEntity projectEntity = new ProjectEntity();
            if (projectEntity.getEmail() != null && !projectEntity.getEmail().isEmpty()) {
                BeanUtils.copyProperties(projectDto, projectEntity);
                projectDto.setCreatedBy(projectDto.getEmail());
                projectDto.setCreatedDate(LocalDate.now());

            }
        return projectRepo.updateEntityDetails(projectEntity);
    }

    @Override
    public ProjectDto updateById(ProjectDto projectDto, ProjectDto sessionDto , MultipartFile multipartFile) {
        log.info("{} Session Dto", sessionDto);

        if (multipartFile.isEmpty()){
            log.info("file is empty");
        }else {
            try {
                byte[] bytes = multipartFile.getBytes();
                Path path  = Paths.get(ServiceConstant.FILE_PATH.getPath()+multipartFile.getOriginalFilename());
                Files.write(path,bytes);
            } catch (IOException e) {
                log.error("{} the error message is :  ",e.getMessage());
            }
        }
        log.info("{} This is the session Dto Passed :  ", sessionDto);
        ProjectEntity entity = new ProjectEntity();
        BeanUtils.copyProperties(projectDto, entity);
        entity.setCreatedBy(String.valueOf(sessionDto.getId()));
        entity.setCreatedBy(sessionDto.getFirstName());
        entity.setCreatedBy(sessionDto.getLastName());
        entity.setCreatedBy(sessionDto.getEmail());
        entity.setCreatedDate(LocalDate.now());
        entity.setUpdatedBy(sessionDto.getCreatedBy());
        entity.setUpdateDate(LocalDate.now());
        entity.setFileName(multipartFile.getOriginalFilename());
        entity.setFileType(multipartFile.getContentType());
        log.info("{} the entity passed is ", entity);
        ProjectEntity projectEntity = projectRepo.updateById(entity);

        ProjectDto projectDto1 = new ProjectDto();
        BeanUtils.copyProperties(projectEntity, projectDto1);
        return projectDto1;

    }

    @Override
    public ProjectDto validateAndSignIn(String email, String pwd) {
        ProjectDto dto = new ProjectDto();
        ProjectEntity entity = projectRepo.getEntityByEmail(email);
        if (entity != null
                && entity.getEmail() != null
                && entity.getEmail().equalsIgnoreCase(email)
                && entity.getPwd() != null
                && passwordEncoder.matches(pwd, entity.getPwd())
                && entity.getAttempt()<=3) {
            log.info("{} {} this is validation of email and pwd : ",email  ,  pwd);
            BeanUtils.copyProperties(entity,dto);
            entity.setAttempt(0);
            projectRepo.updateEntityDetails(entity);
            return dto;
        }else {

            ProjectEntity projectEntity = projectRepo.getEntityByEmail(email);
            log.info("the entity is: {}",projectEntity);
            projectEntity.setAttempt(projectEntity.getAttempt()  +1);
            log.info("the entity after entering the password is : {}",entity);
            ProjectDto   projectDto = new ProjectDto();
            projectDto.setAttempt(projectEntity.getAttempt());
            return projectDto;

        }


    }

    @Override
    public boolean validateOtp(String otp, String email) {

        ProjectEntity projectEntity  = projectRepo.getEntityByEmail(email);
        if ( projectEntity.getEmail()!= null
                && projectEntity.getEmail().equalsIgnoreCase(email)
                && projectEntity.getOtp()!= null
                && passwordEncoder.matches(otp,projectEntity.getOtp())){
            log.info(MessageConstant.OTP_VALIDATE_SUCCESS_MESSAGE.getMessage());
            return true;

        }else {
            log.info(MessageConstant.OTP_VALIDATE_UN_SUCCESS_MESSAGE.getMessage());
        }

        return false;
    }
}
