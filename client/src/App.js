import React from "react";
import AskQuestion from "./components/AskQuestion";
import Main from "./pages/Main";
import EditQuestion from "./components/EditQuestion";
import EditAnswer from "./components/EditAnswer";


function App() {
  return (
    <>
    <Main />
    <AskQuestion/>
    <EditQuestion/>
    <EditAnswer/>
    </>
  );
}

export default App;

