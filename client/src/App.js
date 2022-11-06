import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from './components/login';
import Header from './components/header/header';
import SignUp from './components/signup';
import Main from "./pages/Main";
import AskQuestion from "./components/AskQuestion";
import Footer from './components/Footer'
import EditQuestion from "./components/EditQuestion";
import EditAnswer from "./components/EditAnswer";
import LoginHeader from "./components/header/loginHeader";

import QuestionLookup from "./pages/QuestionsLookup";

import { useEffect, useState  } from 'react';
import QuestionLook from "./components/QuestionLook";


function App() {

  const [isLogin, setIsLogin] = useState(false)
 
  useEffect(() => {
    if(sessionStorage.getItem('username') === null){
    // sessionStorage 에 username이라는 key 값으로 저장된 값이 없다면
    } else {
    // sessionStorage 에 username이라는 key 값으로 저장된 값이 있다면
    // 로그인 상태 변경
      setIsLogin(true)
    }
  })

  return (
    <BrowserRouter>
    {/* isLogin 값이 true라면 로그아웃이 있는 헤더로 아니라면 그냥 헤더로 변환 */}
      {isLogin ? <LoginHeader isLogin={isLogin}/> : <Header/>}  
        <Routes>
          <Route path="/" element={<Main/>}></Route>
          <Route path="/login" element={<Login />}></Route>
          <Route path="/signup" element={<SignUp/>}></Route>
          <Route path="/ask" element={<AskQuestion/>}></Route>
          <Route path="/questions/:QuestionId" element={<QuestionLookup/>}></Route>
          <Route path="/editQ" element={<EditQuestion/>}></Route>
          <Route path="/editA" element={<EditAnswer/>}></Route>
          <Route path="/editA" element={<EditAnswer/>}></Route>
        </Routes>
        <Footer />
    </BrowserRouter>


  );
}

export default App;
