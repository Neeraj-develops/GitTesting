package com.spring.Services.Imple_Class;

import com.spring.Dtos.Electro_UsersDto;
import com.spring.Dtos.PageAbleResponse;
import com.spring.Entity.Electro_Users;
import com.spring.Exceptions.ResourceNotFoundException;
import com.spring.Helper.PagebleResponseHelper;
import com.spring.Repositories.User_Repo;
import com.spring.Services.Interface.UsersService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class Users_Services_imple implements UsersService {

    Logger log = LoggerFactory.getLogger(Users_Services_imple.class);

    @Autowired
    private User_Repo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Electro_UsersDto create_Users(Electro_UsersDto userDtos) {
        //generate uniqure iQ
        String userId=UUID.randomUUID().toString();
        userDtos.setUserID(userId);
        Electro_Users user= dto_to_Entity(userDtos);
        Electro_Users savedUser= userRepo.save(user);
        Electro_UsersDto dtoUser= entity_to_Dto(savedUser);
        return dtoUser;
    }



    @Override
    public Electro_UsersDto update_Users(Electro_UsersDto userDtos, String userID) {
        Electro_Users user=userRepo.findById(userID)
                .orElseThrow(()-> new ResourceNotFoundException("user not found"));
        user.setUserName(userDtos.getUserName());
        user.setUserEmail(userDtos.getUserEmail());
        user.setUserPassword(userDtos.getUserPassword());
        user.setUserAbout(userDtos.getUserAbout());
        user.setUserGender(userDtos.getUserGender());
        user.setUserImg(userDtos.getUserImg());

        Electro_Users updatedUser= userRepo.save(user);
        Electro_UsersDto userDto = entity_to_Dto(updatedUser);
        return userDto;
    }

    @Override
    public void deleteUser(String userID) {
        Electro_Users user=userRepo.findById(userID)
                .orElseThrow(()-> new ResourceNotFoundException("user not found"));
        userRepo.delete(user);

    }

    @Override
    public PageAbleResponse<Electro_UsersDto> getAll_Users(int pageNumber,
                                         int pageSize,
                                         String sortBy,
                                         String sortDirection) {
        Sort sort=(sortDirection.equalsIgnoreCase("desc"))?
                (Sort.by(sortBy).descending()):(Sort.by(sortBy).ascending());

        Pageable pageable = PageRequest.of(pageNumber-1,pageSize,sort);
        Page<Electro_Users> page= userRepo.findAll(pageable);

        PageAbleResponse<Electro_UsersDto> respo= PagebleResponseHelper.pageAbleHelper(page, Electro_UsersDto.class);
        return respo;
    }

    @Override
    public Electro_UsersDto getSingle_User(String userID) {
        Electro_Users user = userRepo.findById(userID)
                .orElseThrow(()->new ResourceNotFoundException("User not fount!!!"));

        return entity_to_Dto(user);
    }

    @Override
    public Electro_UsersDto getSingle_User_by_email(String userEmail) {
       Electro_Users user= userRepo.findByuserEmail(userEmail)
               .orElseThrow(()->new ResourceNotFoundException("User not fount!!!"));


        return entity_to_Dto(user);
    }

    @Override
    public List<Electro_UsersDto> search_User(String keyword) {
        List<Electro_Users> users= userRepo.findByuserNameContaining(keyword);
        System.out.println(users);
        log.info("this about the ifo");
        List<Electro_UsersDto> listDtos= users.stream()
                .map(this::entity_to_Dto)
                .toList();
        log.info("this about the user");
        System.out.println(listDtos);
        return listDtos;
    }

    private Electro_UsersDto entity_to_Dto(Electro_Users savedUser) {
//        Electro_UsersDto  electroUsers= Electro_UsersDto.builder()
//                .userID(savedUser.getUserID())
//                .userName(savedUser.getUserName())
//                .userEmail(savedUser.getUserEmail())
//                .userPassword(savedUser.getUserPassword())
//                .userAbout(savedUser.getUserAbout())
//                .userGender(savedUser.getUserGender())
//                .userImg(savedUser.getUserImg())
//                .build();
        return  modelMapper.map(savedUser,Electro_UsersDto.class);
    }

    private Electro_Users dto_to_Entity(Electro_UsersDto userDtos) {
//        Electro_Users user=Electro_Users.builder()
//                .userID(userDtos.getUserID())
//                .userName(userDtos.getUserName())
//                .userAbout(userDtos.getUserAbout())
//                .userEmail(userDtos.getUserEmail())
//                .userGender(userDtos.getUserGender())
//                .userImg(userDtos.getUserImg())
//                .userPassword(userDtos.getUserPassword())
//                .build();
        return modelMapper.map(userDtos, Electro_Users.class);
    }
}
