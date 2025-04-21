package com.mysticarts.newapp.entity;
import javax.persistence.*;
import com.mysticarts.newapp.dto.Gender;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "new_year_event")
@Entity
@NamedQueries({
        @NamedQuery(name = "getEntityByEmail",query = "select pe from ProjectEntity pe where pe.email=: email"),
        @NamedQuery(name = "getEntityById",query = "select pe from ProjectEntity pe where pe.id=: id"),
        @NamedQuery(name = "updatePasswordByEmail",query = "update ProjectEntity p set p.pwd =: pwd, p.confirmPwd =: confirmPwd where p.email=:email"),
        @NamedQuery(name = "checkPhone",query = "select count(p) from ProjectEntity p where p.phone =: phone"),
        @NamedQuery(name = "checkEmail",query = "select count(p)  from  ProjectEntity p where p.email=: email "),
        @NamedQuery(name = "save",query = "select count(p)  from ProjectEntity p where p.email =: email ")

})

public class ProjectEntity {



        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Integer id;

        @Column(name = "email")
        private String email;

        @Column(name = "user_name")
        private String userName;

        @Column(name = "first_name")
        private String firstName;

        @Column(name = "last_name")
        private String lastName;

        @Column(name = "age")
        private  Integer age;

        @Column(name = "phone")
        private Long phone;

        @Column(name = "pwd")
        private String pwd;

        @Column(name = "confirm_pwd")
        private String confirmPwd;

        @Column(name = "gender" )
        @Enumerated(EnumType.STRING)
        private Gender gender;

        @Column(name = "created_by")
        private String createdBy;

        @Column(name = "created_on")
        private LocalDate createdDate;

        @Column(name = "updated_by")
        private String updatedBy;

        @Column(name = "updated_on")
        private LocalDate updateDate;

        @Column(name = "created_on_time")
        private LocalTime createdTime;

        @Column(name = "file_name")
        private String fileName;

        @Column(name = "file_type")
        private String fileType;

        @Column(name = "attempt_no_time")
        private int attempt;

        @Column(name = "otp")
        private  String otp;

}

