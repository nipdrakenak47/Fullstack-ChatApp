package com.kalal165code.crudappdemo.rest;

import com.kalal165code.crudappdemo.entity.UserChat;
import com.kalal165code.crudappdemo.service.UserChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userchat")
@CrossOrigin
public class UserChatController {

    private UserChatService theUserChatService;

    @Autowired
    public UserChatController(UserChatService theUserChatService){
        this.theUserChatService = theUserChatService;
    }

    @GetMapping("/getchat")
    public List<UserChat> getChatList(@RequestParam(name = "fromUser") String fromUser,
                                      @RequestParam(name = "toUser") String toUser){
        return theUserChatService.getChat(fromUser, toUser);
    }

    @PostMapping("/postchat")
    public void setChat(@RequestBody UserChat theUserchat){
        UserChat newUser = new UserChat(theUserchat.getFromUser(),theUserchat.getToUser(),
                theUserchat.getChatMessage(),theUserchat.getChatTime());
        theUserChatService.save(newUser);
    }

}
