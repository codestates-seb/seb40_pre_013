import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from './components/login';
import Header from './components/header/header';
import SignUp from './components/signup';
import Main from "./pages/Main";
import AskQuestion from "./components/AskQuestion";
import QuestionLookup from "./components/questionLookup";


function App() {
  return (
    <BrowserRouter>
      <div>
        <Header/>
        <Routes>
          <Route path="/" element={<Main/>}></Route>
          <Route path="/login" element={<Login />}></Route>
          <Route path="/signup" element={<SignUp/>}></Route>
          <Route path="/ask" element={<AskQuestion/>}></Route>
          <Route path="/qlookup" element={<QuestionLookup/>}></Route>
        </Routes>
      </div>
    </BrowserRouter>


  );
}

export default App;
