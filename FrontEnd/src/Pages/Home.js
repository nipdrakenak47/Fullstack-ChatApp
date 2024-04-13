import React, { useEffect, useState } from 'react';
import Sidebar from '../HomeComp/Sidebar';
import Chat from '../HomeComp/Chat';
import '../HomeComp/comp.css'
import { StompClientContext } from '../context/StompClientContext';
import SockJS from 'sockjs-client';
import { over } from 'stompjs';
import {BASE_URL} from '../base_url';


export default function Home() {
  const [stompInstance,setStompInstance] = useState(null);

  const connect = ()=>{
    const socket = new SockJS(`${BASE_URL}/ws`);
    const stompClient = over(socket);

    stompClient.connect({},function(frame){
      console.log("Socket connected..."+frame);
    });

    setStompInstance(stompClient);
  }

  useEffect(()=>{
    connect();
  },[])
  
  return (
    <StompClientContext.Provider value = {stompInstance}>

    <div className='home'>
        <div className='window'>
            <Sidebar></Sidebar>
            <Chat></Chat>
        </div>
    </div>
    </StompClientContext.Provider>
  )
}
