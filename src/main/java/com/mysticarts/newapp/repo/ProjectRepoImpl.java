package com.mysticarts.newapp.repo;
import com.mysticarts.newapp.entity.ProjectEntity;
import com.mysticarts.newapp.exception.EmailNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.*;


@Slf4j
@Repository
public class ProjectRepoImpl implements ProjectRepo {


    @Autowired
    EntityManagerFactory factory;


    @Override
    public boolean save(ProjectEntity entity) {

        try {
            EntityManager entityManager = factory.createEntityManager();
            log.info("{} the email sent from entity :  ",entity.getEmail());
            Query query = entityManager.createNamedQuery("save").setParameter("email", entity.getEmail());

            Long count = (Long) query.getSingleResult();
            if (count > 0) {
                log.info("{} Sorry the email you have already registered :  ",entity.getEmail());
                return false;
            } else {
                entityManager.getTransaction().begin();
                entityManager.persist(entity);
                entityManager.getTransaction().commit();
                entityManager.close();
                log.info("{} User is Saved ",entity);


            }
        } catch (EmailNotFoundException e) {
            throw new RuntimeException(e);

        }
        return true;
    }

    @Override
    public ProjectEntity getEntityByEmail(String email) {
        log.info("{} this is to login : ",email);
        EntityManager entityManager = factory.createEntityManager();
        try {

            entityManager.getTransaction().begin();
            Query query = entityManager.createNamedQuery("getEntityByEmail");
            query.setParameter("email", email);
            Object singleResult = query.getSingleResult();

            return (ProjectEntity) singleResult;

        } catch (NoResultException e) {
            return null; // Handle no result gracefully
        } finally {
            entityManager.close();
        }
    }


    @Override
    public boolean updatePasswordByEmail(String email, String pwd, String confirmPwd) {
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createNamedQuery("updatePasswordByEmail");
        query.setParameter("email", email);
        query.setParameter("pwd", pwd);
        query.setParameter("confirmPwd", confirmPwd);
        query.executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();

        return true;
    }

    @Override
    public boolean checkEmail(String email) {

         EntityManager entityManager = factory.createEntityManager();
        try {
            Query query    = entityManager.createNamedQuery("checkEmail");
            query.setParameter("email",email);
            Long count = (Long)  query.getSingleResult();
            return count > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;

        }
    }

    @Override
    public boolean checkPhone(Long phone) {
        EntityManager entityManager = factory.createEntityManager();
        try {
            Query query = entityManager.createNamedQuery("checkPhone");
            query.setParameter("phone",phone);
            Long count = (Long)query.getSingleResult();
            return count>0 ;
        }catch (Exception e){
            log.info(" the error is : {} ", e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateEntityDetails(ProjectEntity projectEntity) {
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        log.info(" projectEntity : {}", projectEntity);
        entityManager.merge(projectEntity);
        entityManager.getTransaction().commit();
        entityManager.close();
        return true;
    }

    @Override
    public ProjectEntity getEntityById(int id) {
        try {
            EntityManager entityManager = factory.createEntityManager();
            entityManager.getTransaction();
            ProjectEntity projectEntity = entityManager.find(ProjectEntity.class, id);
            entityManager.close();
            return projectEntity;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ProjectEntity updateById(ProjectEntity projectEntity) {
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        log.info("{} projectEntity", projectEntity);
        ProjectEntity projectEntity1 = entityManager.merge(projectEntity);
        entityManager.getTransaction().commit();
        entityManager.close();
        return projectEntity1;
    }

}


