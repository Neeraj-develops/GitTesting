package com.spring.Dtos;

import com.spring.Helper.ImageNameValidator;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Electro_UsersDto {

    private String userID;

    private String userName;

//    @Email(message = "Invalid Email!!!!")
    @Pattern(regexp = "^[a-z0-9][-a-z0-9._]+@([-a-z0-9]+\\.)+[a-z]{2,5}$",
            message = "Please fill the valid email")
    @NotBlank(message = "Please fill the field!!!")
    private String userEmail;

    @Size(min = 5, max = 20, message = "Please fill the adequate password")
    @NotBlank(message = "Please fill the field")
    private String userPassword;

    private String userGender;

    private String userAbout;

    @ImageNameValidator
    private String userImg;
}
