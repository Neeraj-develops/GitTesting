package com.spring.Services.Interface;

import com.spring.Dtos.Electro_UsersDto;
import com.spring.Dtos.PageAbleResponse;
import com.spring.Entity.Electro_Users;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UsersService {

    //create

    Electro_UsersDto create_Users(Electro_UsersDto userDtos);

    //update
    Electro_UsersDto update_Users(Electro_UsersDto userDtos,String userID);

    //delete
    void deleteUser(String userID);

    // get all users
    PageAbleResponse<Electro_UsersDto >getAll_Users(int pageNumber , int pageSize, String sortBy, String sortDirection);

    //get single users by id
    Electro_UsersDto getSingle_User(String userID);

    //get single user by email
    Electro_UsersDto getSingle_User_by_email(String userEmail);

    //search users
    List<Electro_UsersDto> search_User(String Keyword);
}
