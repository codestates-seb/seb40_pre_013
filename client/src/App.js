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
import QuestionLook from "./components/QuestionLook";

function App() {
  return (
    <BrowserRouter>
      <Header/>
        <Routes>
          <Route path="/" element={<Main/>}></Route>
          <Route path="/login" element={<Login />}></Route>
          <Route path="/signup" element={<SignUp/>}></Route>
          <Route path="/ask" element={<AskQuestion/>}></Route>
          <Route path="/qlookup" element={<QuestionLook/>}></Route>
          <Route path="/editQ" element={<EditQuestion/>}></Route>
          <Route path="/editA" element={<EditAnswer/>}></Route>
        </Routes>
        <Footer />
    </BrowserRouter>


  );
}

export default App;
