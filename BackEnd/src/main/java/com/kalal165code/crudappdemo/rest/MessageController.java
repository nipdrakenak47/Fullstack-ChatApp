package com.kalal165code.crudappdemo.rest;

import com.kalal165code.crudappdemo.entity.UserChat;
import com.kalal165code.crudappdemo.service.UserChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private UserChatService theUserChatService;

    @Autowired
    public MessageController(UserChatService theUserChatService){
        this.theUserChatService = theUserChatService;
    }



    @MessageMapping("/message")
    @SendTo("/topic/return-to")
    public UserChat recieveMessage(@RequestBody UserChat theMessage){

        UserChat newUser = new UserChat(theMessage.getFromUser(),theMessage.getToUser(),
                theMessage.getChatMessage(),theMessage.getChatTime());
        theUserChatService.save(newUser);

        UserChat originalMessage = theUserChatService.getUserChat(theMessage);

        System.out.println(originalMessage);

        return originalMessage;
    }
}
