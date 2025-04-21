package com.mysticarts.newapp.repo;

import com.mysticarts.newapp.entity.ProjectEntity;

public interface ProjectRepo {

    boolean save(ProjectEntity entity);

    ProjectEntity getEntityByEmail(String email);

    boolean updatePasswordByEmail(String email, String pwd, String confirmPwd);

    boolean checkEmail(String email);

    boolean checkPhone(Long phone);

/*    String sendOtp();*/

  boolean updateEntityDetails(ProjectEntity projectEntity);

 ProjectEntity getEntityById(int id);

 ProjectEntity updateById(ProjectEntity projectEntity);
}
