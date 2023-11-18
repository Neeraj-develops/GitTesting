package com.spring.Controller;

import com.spring.Dtos.ApiResponseMessage;
import com.spring.Dtos.Electro_UsersDto;
import com.spring.Dtos.ImageResponse;
import com.spring.Dtos.PageAbleResponse;
import com.spring.Services.Interface.FileService;
import com.spring.Services.Interface.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Value("${user.profile.image.path}")
    private String imageUploadPath;
    @Autowired
    private UsersService usersService;

    @Autowired
    FileService fileService;

    @PostMapping
    public ResponseEntity<Electro_UsersDto> createUser (
          @Valid @RequestBody Electro_UsersDto usersDto
    ){
      Electro_UsersDto electroUsersDto = usersService.create_Users(usersDto);
        System.out.println("this from maaster branch hello everyone");
      return new ResponseEntity<>(electroUsersDto, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Electro_UsersDto> updateUser(
            @PathVariable String userId,
           @Valid @RequestBody Electro_UsersDto userDto
    ){
        Electro_UsersDto electroUsersDto = usersService.update_Users(userDto, userId);
        return  new ResponseEntity<>(electroUsersDto, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponseMessage> deleteUser(
            @PathVariable String userId
    ){
        usersService.deleteUser(userId);
        ApiResponseMessage mes= ApiResponseMessage.builder()
                .message("deleted successfully")
                .success(true)
                .status(HttpStatus.OK)
                .build();

        return  new ResponseEntity<>(mes, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PageAbleResponse<Electro_UsersDto>> getAllUsers(
            @RequestParam(value="pageNumber", required = false) int pageNumber,
            @RequestParam(value="pageSize", required = false) int pageSize,
            @RequestParam(value="sortBy", required = false) String sortBy,
            @RequestParam(value="sortDirection", required = false) String sortDirection
    ){
        System.out.println("this is get all controller");
        return  new ResponseEntity<>(usersService.getAll_Users(pageNumber, pageSize,sortBy,sortDirection), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Electro_UsersDto> getUserByID(
            @PathVariable String userId
    ){
        return new ResponseEntity<>(usersService.getSingle_User(userId),HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Electro_UsersDto> getUserByEmail(
            @PathVariable String email
    ){
        return new ResponseEntity<>(usersService.getSingle_User_by_email(email),HttpStatus.OK);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<Electro_UsersDto>> getBykeywords(
            @PathVariable String keyword
    ){
        System.out.println("this is doing something or not");
        return new ResponseEntity<>(usersService.search_User(keyword),HttpStatus.OK);
    }

    @PostMapping("/images/{userID}")
    public ResponseEntity<ImageResponse> uploadFile(
            @RequestParam("userImage")MultipartFile userImage,
            @PathVariable String userID
    ) throws IOException {
        String imageName= fileService.uploadFile(userImage, imageUploadPath);

        Electro_UsersDto usersDto= usersService.getSingle_User(userID);
        usersDto.setUserImg(imageName);
        Electro_UsersDto updateDto= usersService.update_Users(usersDto,userID);

        ImageResponse imageResponse= ImageResponse.builder()
                .imagName(imageName)
                .message("Image upload successfully")
                .success(true)
                .status(HttpStatus.CREATED)
                .build();
        return new ResponseEntity<>(imageResponse, HttpStatus.CREATED);

    }

}
