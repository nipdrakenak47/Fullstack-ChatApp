import React from "react";
import "./Register.css";
import Add from "../Images/addAvatar.png";
import { useState } from "react";
import { Link } from "react-router-dom";
import {BASE_URL} from '../base_url';



export default function Register() {
  const [formstatus, setFormstatus] = useState({success : false,error : false,message : ''});

  function validateData(formData){
    for(let item of formData){

      if(item[1]===null || item[1]===''){
        return true;
      }
    }

    return false;

  }


  const submitHandler =  async (e) => {
    e.preventDefault();

    let form = document.getElementById('regForm');

    const formData = new FormData(form);

    if(validateData(formData)){
      setFormstatus({success : false,error : true,message : 'Entered details not valid !'});
      return ;
    }

    formData.append("imgFlag",0);

    if(formData.get("filedata")!==null){
      formData.set("imgFlag",1);
    }

    fetch(`${BASE_URL}/users/post`,{
      method : "POST",
      body : formData
    })
    .then(async (response)=>{
      if(!response.ok){
        throw new Error('Connection issue...');
      }
      else{
        let res1 = await response.json();

        if(res1.exist==='YES'){
          setFormstatus({success : false,error : true,message : 'User already exist !'});
        }
        else{
          setFormstatus({success : true,error : false,message : ''});
        }
      }
    })
    .catch(error => {
      setFormstatus({success : false,error : true,message : 'Something went wrong !'});
    })
  };



  return (
    <div className="formContainer" id="formContainer">
      <div className="formWrapper">
        <div className='register-header'>
          <h1>Register</h1>
      </div>
        <form onSubmit={submitHandler} className="registerForm" id="regForm">

        <div id='user-div'>
            <input type="text" name="username" id="" placeholder="Username" size="30"/>
            {/* <BiSolidUser className='icon'></BiSolidUser> */}
        </div>
                   
        <br />

        <div id='pwd-div'>
              <input type="password" name="password" placeholder="Password" size="30"/>
              {/* <RiLockPasswordFill className='icon'></RiLockPasswordFill> */}
        </div>
                    
        <br />

          <label htmlFor="input-file" className="input-file">
            <img src={Add} alt="no" />
            <span>Add an avatar</span>
          </label>
          <input
            style={{ display: "none" }}
            type="file"
            name="filedata"
            id="input-file"
          />
          <button className="btn-1">Sign Up</button>
        </form>

        <div className="last">
        <p>Have an account?  <Link to='/login'>Login</Link></p>
          </div>

        {/* <button onClick={handlerClick}>Clcik me to get Image</button> */}

        {formstatus.error && 
        <div className="message>" style={{textAlign:"center",marginTop:"20px"}}><h3 style={{color:"rgb(227, 90, 90)"}}>{formstatus.message}</h3></div>}

        {formstatus.success && 
        <div className="message>" style={{textAlign:"center",marginTop:"20px"}}><h3 style={{color:"green"}}>Registration Successfull !</h3></div>}

      </div>
    </div>
  );
}
