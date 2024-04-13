import React from 'react'
import { useContext } from 'react'
import { AuthContext } from '../context/AuthContext'
import { ChatContext } from '../context/ChatContext'



export default function Nav() {
  const auth = useContext(AuthContext);
  const {dispatch} = useContext(ChatContext);

  function clickHandler(){
    auth.setCurrentUser(null);
    dispatch({type:"lgout_user"});
  }


  return (
    <div className='nav'>
      <div className='user-self'>
        <img src={auth.currentUser.imageUrl} alt="" />
        <span>{auth.currentUser.username}</span>
        <button onClick={clickHandler}>Logout</button>
      </div>
    </div>
  )
}
