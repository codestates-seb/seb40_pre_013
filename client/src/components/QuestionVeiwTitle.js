import React from "react";
import styled from "styled-components";
import { useNavigate } from "react-router-dom";


const QuestionsViewTitle = ({title,time,modifie}) => {
    const navigate = useNavigate();
    const handleAskBtnClick = () => {
        if(sessionStorage.getItem('username') !== null) {
          navigate("/ask");
        }
        else {
          alert('로그인을 먼저 하고 오세요!');
          window.location.href = '/login';
        }
      };

  return (

    <Container>
      <QuestionWrap>
        <QuestionHeader>
            <h1>{title}</h1>
          <button onClick={handleAskBtnClick}> Ask Qustion</button>
        </QuestionHeader>
        <QuestionDate>
          <div>Asked {time}</div>
          <div>Modified {modifie}</div>
          <div>Viewed 7 times</div>
        </QuestionDate>
      </QuestionWrap>
    </Container>
  );
};

export default QuestionsViewTitle ;

const Container = styled.div`
  display: flex;
  max-width: 1264px;
  width: 100%;
  border-right-width: 0;
`;

const QuestionWrap = styled.div`
  width: 100%;
  max-width: 1100px;
  border-right-width: 0;
`;

const QuestionHeader = styled.div`
  display: flex;
  justify-content: space-between;
  margin: 0 0 8px;

  h1 {
    font-size: 27px;
    margin: 0;
  }
  button {
    position: relative;
    display: inline-block;
    padding: 10px;
    height: 35px;
    color: #ffffff;
    border: 1px solid #0000;
    border-radius: 3px;
    background-color: #0078d2;
    outline: none;
    font-size: 13px;
    font-weight: normal;
    text-align: center;
    text-decoration: none;
    cursor: pointer;
    user-select: none;
    box-shadow: inset 0 1px 0 0 #fff6;
    &:hover {
      background-color: #0063bf;
    }
  }
`;

const QuestionDate = styled.div`
  padding: 0 0 16px;
  border-color: hsl(210, 8%, 90%);
  border-bottom-style: solid;
  border-bottom-width: 1px;
  display: flex;

  div {
    color: hsl(210, 8%, 45%);
    font-size: 13px;
    padding-right: 8px;
    display: flex;
  }
`;

