import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from './components/login';
import Header from './components/header/header';



function App() {
  return (
    <BrowserRouter>
      <div>
        <Header></Header>
        <Routes>
          <Route path="/login" element={<Login />}></Route>
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
