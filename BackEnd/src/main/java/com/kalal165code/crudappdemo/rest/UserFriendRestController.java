package com.kalal165code.crudappdemo.rest;

import com.kalal165code.crudappdemo.entity.UserFriend;
import com.kalal165code.crudappdemo.service.UserFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userfriend")
@CrossOrigin
public class UserFriendRestController {
    private UserFriendService theUserFriendService;

    @Autowired
    public UserFriendRestController(UserFriendService theUserFriendService){
        this.theUserFriendService = theUserFriendService;
    }

    @GetMapping("/{username}")
    public List<UserFriend> findAll(@PathVariable String username){
        return theUserFriendService.getListByUserName(username);
    }

    @PostMapping("/postUserFriend")
    public void postUserFriend(@RequestBody UserFriend theUserFriend){
        theUserFriendService.save(theUserFriend);
    }
}
