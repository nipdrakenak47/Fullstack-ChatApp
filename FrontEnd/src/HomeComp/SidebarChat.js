import React, { useState,useEffect,useContext } from 'react'
import UserDefaultUrl from "../Images/user.jpeg"
import { AuthContext } from '../context/AuthContext';
import { ChatContext } from '../context/ChatContext';
import {BASE_URL} from '../base_url';



export default function SidebarChat(props) {
    
    const [chats,setChats] = useState([]);
    const auth = useContext(AuthContext);
    const {dispatch} = useContext(ChatContext);


async function fetchImageUrl(username){

  let url = `${BASE_URL}/users/get-file/${username}`;

  let blob = await fetch(url)
  .then((response)=>{
    if(!response.ok){
      throw Error("couldn't fetch the profile picture");
    }
    return response.blob()
  })
  .catch(err=>{
    console.log(err);
  })

  if(blob.size===0){
    return UserDefaultUrl;
  }
  else{
    let imageUrl = URL.createObjectURL(blob);
    return imageUrl;
  }

}


    useEffect(()=>{

      const getFriendList = async () => {

        let url = `${BASE_URL}/userfriend/${auth.currentUser.username}`;
        
        fetch(url)
        .then(async (response)=>{
          let responseJson = await response.json();
          const len = auth.currentUser.username.length+1;
          const chatArr = [];

          for(let item of responseJson){
            item.userFriendName = item.userFriendName.substring(len);
            item.imageUrl = await fetchImageUrl(item.userFriendName);
            chatArr.push(item);
          }

          chatArr.sort((item1,item2)=>{
            return parseInt(item2.messageTime)-parseInt(item1.messageTime)
          })

          setChats(chatArr);
        })
        .catch(err =>{
          throw err;
        })
        
      }

      getFriendList();       
       
    },[props.flag]);

    const selectHandler = (u)=>{
      u.currentUserImageUrl = auth.currentUser.imageUrl;
      u.currentUser = auth.currentUser.username;
      dispatch({type:"CHANGE_USER",payload:u});
  }


  return (
    <div className='sidebarchat'>

      { chats.map((chat)=>(
        
        <div key={chat.id}  className='user' onClick={()=>selectHandler(chat)}>
                <img  src={chat.imageUrl} alt="" />
                <div className='user-info'>
                    <span>{chat.userFriendName}</span>
                </div>
            </div>
    ))
    }
    </div>
  )
}
