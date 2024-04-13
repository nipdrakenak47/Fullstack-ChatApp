import React, { useState } from 'react';
import Nav from './Nav';
import Search from './Search';
import SidebarChat from './SidebarChat';
import { ChatChangeContext } from "../context/ChatChangeContext";


export default function Sidebar() {

  const [changeFlag,setChangeFlag] = useState(true);


  return (
    <ChatChangeContext.Provider value = {{changeFlag,setChangeFlag}}>

      <div className='sidebar'>
        <Nav></Nav>
        <Search></Search>
        <SidebarChat flag={changeFlag}></SidebarChat>
      </div>
    </ChatChangeContext.Provider>
  )
}
