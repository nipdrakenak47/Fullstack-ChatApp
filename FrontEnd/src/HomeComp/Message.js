import React, { useContext, useEffect, useRef } from 'react'
import { ChatContext } from '../context/ChatContext';


export default function Message({message}) {

  const ref = useRef()
  const {data} = useContext(ChatContext);

  useEffect(()=>{
    ref.current?.scrollIntoView({behavior:'smooth'})
  },[message])


  let cname,user_content,image_url;
  if(message.fromUser===data.currentUser){
    cname = "message-owner";
    user_content = 'You';
    image_url = data.currentUserImageUrl;
  }
  else{
    cname = "message"
    user_content = message.fromUser;
    image_url = data.friendImageUrl;
  }
  return (
    <div ref={ref} className={cname}>
      <div className='message-info'>
        <img src={image_url} alt="" />
        <span style={{color:"black"}}>{user_content}</span>
      </div>
      <div className='message-content'>
        <span>{message.chatMessage}</span>
      </div>
    </div>
  )
}
