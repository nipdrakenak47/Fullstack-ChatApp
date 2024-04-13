import Login from "./Pages/Login.js";
import Register from "./Pages/Register.js";
import Home from "./Pages/Home.js";
import "./Pages/Login.css";
import { BrowserRouter as Router, Routes, Route,Navigate } from "react-router-dom";
import { AuthContext } from "./context/AuthContext.js";
import { useState } from "react";


function App() {

  const [currentUser,setCurrentUser] = useState(null)

  const ProtectedRoute = (props)=>{
    if(currentUser===null){
      return <Navigate to='login'></Navigate>;
    }
    else{
      return  props.children;
    }
  };
  
  return (
    <AuthContext.Provider value = {{currentUser,setCurrentUser}}>

    <Router>
        <Routes>
        <Route path='/'>
          <Route index element={<ProtectedRoute>
                                  <Home></Home>
                                </ProtectedRoute>}/>
          <Route path='login' element={<Login/>}></Route>
          <Route path='register' element={<Register/>}></Route>
        </Route>
        </Routes>
    </Router>

    </AuthContext.Provider>
  );
}

export default App;
