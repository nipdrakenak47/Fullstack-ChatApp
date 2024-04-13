package com.kalal165code.crudappdemo.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "user_friend")
public class UserFriend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "user_friend_name")
    private String userFriendName;

    @Column(name = "last_message")
    private String lastMessage;

    @Column(name = "message_time")
    private String messageTime;

    public UserFriend(){}

    public UserFriend(String userFriendName, String lastMessage, String messageTime) {
        this.userFriendName = userFriendName;
        this.lastMessage = lastMessage;
        this.messageTime = messageTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserFriendName() {
        return userFriendName;
    }

    public void setUserFriendName(String userFriendName) {
        this.userFriendName = userFriendName;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }

    @Override
    public String toString() {
        return "UserFriend{" +
                "userFriendName='" + userFriendName + '\'' +
                ", lastMessage='" + lastMessage + '\'' +
                ", messageTime='" + messageTime + '\'' +
                '}';
    }
}
