import React, { useContext, useState } from 'react'
import MessageWindow from './MessageWindow'
import MessageInput from './MessageInput'
import { ChatContext } from '../context/ChatContext'
import { ChatUpdateContext } from '../context/ChatUpdateContext';
import { newMessageContext } from '../context/NewMessageContest';
import "./comp.css";


export default function Chat() {
  const {data} = useContext(ChatContext);

  const [chatUpdate,setChatUpdate] = useState(true);
  const [newMessage,setNewMessage] = useState({fromUser:null});

  if(!data.chatId){
    return (
      <>
    <div className='chat'>
      <div className='upper'></div>
      <div className='chat-message-window'>
        <p>Choose a Chat to start Conversation!</p>
      </div>
    </div>
      
      </>
    )
  }
  else{
    
    return (
      <ChatUpdateContext.Provider value={{chatUpdate,setChatUpdate}}>
        <newMessageContext.Provider value={setNewMessage}>


      <div className='chat'>
      <div className='upper'>
        <img src={data.friendImageUrl} alt="" />
        <span>{data.friendName}</span>
      </div>
      <MessageWindow message = {newMessage}></MessageWindow>
      <MessageInput></MessageInput>
    </div>
        </newMessageContext.Provider>

      </ChatUpdateContext.Provider>
  )
}
}
