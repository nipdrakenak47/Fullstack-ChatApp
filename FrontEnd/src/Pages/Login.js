import React from "react";
import "./Login.css";
import { useState,useContext } from "react";
import { AuthContext } from "../context/AuthContext";
import { useNavigate,Link } from "react-router-dom";
import { BiSolidUser } from "react-icons/bi";
import { RiLockPasswordFill } from "react-icons/ri";
import UserDefaultUrl from "../Images/user.jpeg";
import {BASE_URL} from '../base_url';



export default function Login() {
  const [err, setErr] = useState({errflag : false,errMessage : ''});
  const auth = useContext(AuthContext);
  const navigate = useNavigate();

  const submitHandler = async (e) => {

    e.preventDefault();

    let form = document.getElementById('Form-login');
    const formData = new FormData(form);

    let payload  = {
      username : formData.get('username'),
      password : formData.get('password')
    };

    fetch(`${BASE_URL}/users/login`,{
      method : "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body : JSON.stringify(payload)
    })
    .then(async (response)=>{

      let responseJson = await response.json();

      if(!response.ok){
        setErr({
          errflag : true,
          errMessage : responseJson.message
        })
      }
      else{
        let url = `${BASE_URL}/users/get-file/${payload.username}`;

          let blob = await fetch(url)
          .then(async (response)=>{
            let blob = await response.blob()
            return blob;
          })
          
          if(blob.size===0){
            auth.setCurrentUser({username : payload.username,imageUrl : UserDefaultUrl});
          }
          else{
            let imageUrl = URL.createObjectURL(blob);
            auth.setCurrentUser({username : payload.username,imageUrl});
          }
          navigate('/');
        
      }
    })
  

  };

  return (
    <div className="formContainer-login">
      <div className="formWrapper-login">

      <div className='login-header'>
          <h1>Login</h1>
      </div>

        <form action="" className="LoginForm-login" id="Form-login" onSubmit={submitHandler}>

        <div id='user-div'>
            <input type="text" name="username" id="" placeholder="Username" size="30"/>
            <BiSolidUser className='icon'></BiSolidUser>
        </div>
                   
        <br />

        <div id='pwd-div'>
              <input type="password" name="password" placeholder="Password" size="30"/>
              <RiLockPasswordFill className='icon'></RiLockPasswordFill>
        </div>
                    
        <br />
                  
          <button className="btn-1-login">Sign In</button>
        </form>

          <div className="last">
          <p className="last-login">Don't have an account? <Link to='/register'>Register</Link></p>
          </div>

          {err.errflag && 
        <div className="message>" style={{textAlign:"center"}}><h3 style={{color:"rgb(227, 90, 90)"}}>{err.errMessage}</h3></div>}
   
      </div>
     </div>
  );
}
