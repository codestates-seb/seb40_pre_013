import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from './components/login';
import Header from './components/header/header';
import SignUp from './components/signup';
import Main from "./pages/Main";
import AskQuestion from "./components/AskQuestion";
// import AskQuestion from "./components/AskQuestion";
// import EditQuestion from "./components/EditQuestion";
// import EditAnswer from "./components/EditAnswer";


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
        </Routes>
        {/* <AskQuestion />
        <EditQuestion />
        <EditAnswer /> */}
      </div>
    </BrowserRouter>


  );
}

export default App;
