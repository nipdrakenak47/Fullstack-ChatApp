package com.kalal165code.crudappdemo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_chat")
public class UserChat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "from_user")
    private String fromUser;

    @Column(name = "to_user")
    private String toUser;

    @Column(name = "chat_message")
    private String chatMessage;

    @Column(name = "chat_time")
    private String chatTime;

    public UserChat(){}

    public UserChat(String fromUser, String toUser, String chatMessage, String chatTime) {
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.chatMessage = chatMessage;
        this.chatTime = chatTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getChatMessage() {
        return chatMessage;
    }

    public void setChatMessage(String chatMessage) {
        this.chatMessage = chatMessage;
    }

    public String getChatTime() {
        return chatTime;
    }

    public void setChatTime(String chatTime) {
        this.chatTime = chatTime;
    }

    @Override
    public String toString() {
        return "UserChat{" +
                "id=" + id +
                ", fromUser='" + fromUser + '\'' +
                ", toUser='" + toUser + '\'' +
                ", chatMessage='" + chatMessage + '\'' +
                ", chatTime='" + chatTime + '\'' +
                '}';
    }
}
