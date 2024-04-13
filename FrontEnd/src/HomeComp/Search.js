import React from 'react'
import { useState,useContext } from 'react'
import { AuthContext } from '../context/AuthContext';
import { ChatChangeContext } from '../context/ChatChangeContext';
import UserDefaultUrl from "../Images/user.jpeg"
import {BASE_URL} from '../base_url';

export default function Search() {

  const [userName,setUserName] = useState("");
  const [user,setUser] = useState(null);
  const [err,setErr] = useState(false);
  const auth = useContext(AuthContext);
  const changeContext = useContext(ChatChangeContext);


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


  const searchHandler = async () => {
    
    let url = `${BASE_URL}/users/${userName}`;


   await fetch(url)
        .then(async (response)=>{

          if(!response.ok){
            throw Error('Something went wrong...');
          }
          let responseJson = await response.json()
          if(responseJson.id===-1){
            setErr(true);
            setTimeout(()=>{
              setErr(false)
            },2000);
          }
          else{
              let imageUrl = await fetchImageUrl(responseJson.userName);
              setUser({userName : responseJson.userName,imageUrl : imageUrl});
          }
        })
        .catch(err=>{
          console.log(err);
        })
  }


  const keyDownHandler = (e) => {
    if(e.code==='Enter'){
      searchHandler();
    }
  }

  const selectHandler = async () => {

     const combine_name = auth.currentUser.username + ' '+ userName;

        let payload  = {
          userFriendName : combine_name,
          lastMessage : "NA",
          messageTime : String(Math.trunc(new Date().getTime() / 1000))
        };
        
         await fetch(`${BASE_URL}/userfriend/postUserFriend`,{
            method : "POST",
            headers: {
            "Content-Type": "application/json",
          },
          body : JSON.stringify(payload)
        })


        const combine_name1 = userName + ' ' + auth.currentUser.username;

        let payload1  = {
          userFriendName : combine_name1,
          lastMessage : "NA",
          messageTime : String(Math.trunc(new Date().getTime() / 1000))
        };
        
        await fetch(`${BASE_URL}/userfriend/postUserFriend`,{
            method : "POST",
            headers: {
            "Content-Type": "application/json",
          },
          body : JSON.stringify(payload1)
        })

        setUser(null);
        setUserName("");

        changeContext.setChangeFlag(!changeContext.changeFlag);

  }

  return (
    <div className='search'>
        <div className='input'>
            <input type="text" placeholder='Search User...' 
            onKeyDown={keyDownHandler} 
            onChange={e=>setUserName(e.target.value)} value={userName}
            />
        </div>
        {err && userName!=='' &&
        <div style={{color:"white",display:"flex",justifyContent:"center",marginBottom:"5px"}}>
          <span>user not found!</span>
        </div> 
        }
        {user && user.userName===userName && 
          <div className='user' onClick={selectHandler}>
            <img src={user.imageUrl} alt="not found" />
            <span>{user.userName}</span>
        </div>}

    </div>
  )
}
