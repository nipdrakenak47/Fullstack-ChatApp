package com.kalal165code.crudappdemo.rest;

import com.kalal165code.crudappdemo.entity.User;
import com.kalal165code.crudappdemo.service.UserService;
import com.kalal165code.crudappdemo.supportclass.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


class UserExist{
    String exist;

    public UserExist(){}
    public String getExist() {
        return exist;
    }
    public void setExist(String exist) {
        this.exist = exist;
    }
}



@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserRestController {

    // defining fields
    private UserService userService;

    // Injecting service object
    @Autowired
    public UserRestController(UserService theUserService){
        this.userService = theUserService;
    }

    @GetMapping("/test")
    public String findAll(){
        return "Hello this is spring boot!";
    }

    @GetMapping("/{username}")
    public User findUser(@PathVariable String username){
        User theUser = userService.getUserByUserName(username);
        if(theUser==null){
            User temp = new User();
            temp.setId(-1);
            return temp;
        }
        else{
            return theUser;
        }
    }


    @PostMapping("/post")
    public UserExist addEmployee(@RequestParam("filedata") MultipartFile file,@RequestParam("username") String userName,
                                 @RequestParam("password") String password){

        User u1 = userService.getUserByUserName(userName);

        UserExist theUserExist = new UserExist();
        theUserExist.setExist("NO");

        if(u1!=null){
            theUserExist.setExist("YES");
            return theUserExist;
        }

        User theUser = new User();
        theUser.setUserName(userName);
        theUser.setPassword(password);

        try{
            byte[] theData = file.getBytes();

            theUser.setFileData(theData);
            userService.save(theUser);
        }
        catch (Exception e){
            System.out.println("ERROR from reading file....!");
            System.out.println(e);
        }
        return theUserExist;
    }


    @PostMapping("/login")
    public ResponseEntity<UserLogin> login(@RequestBody UserLogin theUserLogin){

        ResponseEntity<UserLogin> responseEntity;

        try {
            User theUser = userService.getUserByUserName(theUserLogin.getUsername());

            if(theUser==null || !theUser.getPassword().equals(theUserLogin.getPassword())){
                theUserLogin.setMessage("Username/Password Invalid !");

                responseEntity = new ResponseEntity<>(theUserLogin, HttpStatus.NOT_FOUND);
            }
            else{
                responseEntity = new ResponseEntity<>(theUserLogin, HttpStatus.OK);
            }
            return responseEntity;
        }
        catch (Exception e){
            System.out.println(e);
            theUserLogin.setMessage("Something went wrong !");

            return new ResponseEntity<>(theUserLogin,HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping(
            value = "/get-file/{username}",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public @ResponseBody byte[] getFile(@PathVariable String username) throws IOException {
        User theUser = userService.getUserByUserName(username);

        if(theUser.getFileData().length==0){
            return null;
        }
        return theUser.getFileData();
    }
}
