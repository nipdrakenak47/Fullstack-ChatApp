import React, { useContext, useEffect, useState } from 'react'
import Message from './Message'
import { ChatContext } from '../context/ChatContext'
import { StompClientContext } from '../context/StompClientContext';
import { newMessageContext } from '../context/NewMessageContest';
import {BASE_URL} from '../base_url';



export default function MessageWindow({message}) {

  const [messages,setMessages] = useState([]);
  const {data} = useContext(ChatContext);
  const stompClient = useContext(StompClientContext);
  const messageSetter = useContext(newMessageContext);


  async function recievedMessage(response){
    let res = JSON.parse(response.body);
      messageSetter(res);
  }


  const checkChatId = async () => {
    
    let url = `${BASE_URL}/userchat/getchat?fromUser=${data.currentUser}&toUser=${data.friendName}`;
      
      fetch(url)
      .then(async (response)=>{
        
        if(!response.ok){
          throw Error('Something went wrong !');
        }


        let responseJson = await response.json();

        const messageArr = [];

        for(let item of responseJson){
          messageArr.push(item);
        }

        messageArr.sort((item1,item2)=>{
          return parseInt(item1.chatTime)-parseInt(item2.chatTime)
        })

        setMessages(messageArr);
      })
      .catch(err =>{
        console.log(err);
        console.log("Please logout and try again.");
      })

  }

  useEffect(()=>{
     stompClient.subscribe("/topic/return-to",recievedMessage);
  },[])


  useEffect(()=>{

    if(data.chatId){
      checkChatId();
    } 

  },[data.chatId]);

  useEffect(()=>{
    let chatArr = [...messages];
    chatArr.push(message);

    if(message.fromUser!==null){
      setMessages(chatArr);
    }
  },[message])


  return (
    <div className='message-window'>
      {messages.map(m=>(
        <Message message={m} key={m.id}></Message>
      ))  }
    </div>
  )
}
