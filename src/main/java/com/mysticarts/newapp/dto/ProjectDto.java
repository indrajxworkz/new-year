package com.mysticarts.newapp.dto;

import lombok.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProjectDto {

    private Integer id;

   @Email
    @Size(min = 10 ,message = "Enter the email Properly")
    @NotEmpty(message = "Enter the email properly")
    private String email;

    @NotNull(message = "This can't be empty")
    private String userName;

    @NotNull
    @Size(min = 3,max = 25,message = "Please enter the First Name ")
    private String firstName;

    @Size(min = 1,max = 25,message = "Please enter the Last Name  ")
    private String lastName;

    @Min(value = 3,message = "Sorry u r  not eligible")
    private  Integer age;

    @NotNull(message = "Please enter your Whatsapp Number ")
    @Min(value = 1000000000L, message = "Phone number must have 10 digits")
    @Max(value = 9999999999L, message = "Phone number must have 10 digits")
    private Long phone;

    @NotNull
    @Size(min = 8,message = "The password doesn't match the requirements ")
    private String pwd;

    @NotNull(message = "the Password don't Match ")
    private String confirmPwd;

    private Gender gender;

    @NotNull(message = "Enter the name  by whom its created * ")
    private String createdBy;

    private LocalDate createdDate;

    private LocalTime createdTime;

    @NotNull(message = "Enter the name by whom its Updated   * ")
    private String updatedBy;

    private LocalDate updateDate;

 private String fileName;

 private String fileType;

private int attempt;

private  String otp;



}
