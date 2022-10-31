import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from './components/login';
import Header from './components/header/header';
import SignUp from './components/signup';
import AskQuestion from "./components/AskQuestion";
import Main from "./pages/Main";
import EditQuestion from "./components/EditQuestion";
import EditAnswer from "./components/EditAnswer";


function App() {
  return (
    <BrowserRouter>
      <div>
        <Header></Header>
        <Routes>
          <Route path="/login" element={<Login />}></Route>
          <Route path="/signup" element={<SignUp/>}></Route>
        </Routes>
        <Main />
        <AskQuestion />
        <EditQuestion />
        <EditAnswer />
      </div>
    </BrowserRouter>


  );
}

export default App;
