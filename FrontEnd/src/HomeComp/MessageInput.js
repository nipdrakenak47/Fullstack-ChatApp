import React, { useState,useContext } from 'react';
import { ChatContext } from '../context/ChatContext';
import { StompClientContext } from '../context/StompClientContext';

export default function MessageInput() {
  
  const [text,setText] = useState("");
  const {data} = useContext(ChatContext);
  const stompClient = useContext(StompClientContext);


  function sendMessage(){

    let message = {
      id : 10,
      fromUser : data.currentUser,
      toUser : data.friendName,
      chatMessage : text,
      chatTime : String(Math.trunc(new Date().getTime() / 1000))
    }

    stompClient.send("/app/message",{},JSON.stringify(message));
  }

  const sendHandler = async ()=>{
    if(text==="" || data.chatId==null){
      return ;
    }
    sendMessage();
    setText("");
  }

  return (
    <div className='message-input'>
      <input type="text" placeholder='Type Something...' onChange={e=>setText(e.target.value)} value={text}/>
      <div className='send'>
        <button className='btn-send' onClick={sendHandler}>Send</button>
      </div>
    </div>
  )
}
