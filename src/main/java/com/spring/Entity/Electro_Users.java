package com.spring.Entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString
@Table(name= "electro_User")
public class Electro_Users {
    @Id
    private String userID;
    private String userName;
    @Column(unique = true)
    private String userEmail;
    @Column(length = 10)
    private String userPassword;
    private String userGender;
    @Column(length = 1000)
    private String userAbout;

    private String userImg;
}
