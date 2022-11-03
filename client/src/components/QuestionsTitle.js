import styled from "styled-components";
import { useNavigate } from "react-router-dom";
import QuestionsList from "./QuestionsList";
import axios from 'axios'
import React, { useState, useEffect } from 'react';

function QuestionsMain() {

  const [questions, setQuestions] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const navigate = useNavigate();
   const handleAskBtnClick = () => {
   navigate("/ask");
   }

   useEffect(() => {

    const fetchQustion = async () => {
      try {
        // 요청이 시작 할 때에는 error 와 questions 를 초기화하고
        setError(null);
        setQuestions(null);
        // loading 상태를 true 로 바꿉니다.
        setLoading(true);
        const response = await axios.get(
          'https://f78a-36-38-67-6.jp.ngrok.io/questions/', {
            headers:{
              "ngrok-skip-browser-warning": "skip"
            }
          }
        )
        console.log(response)
        setQuestions(response.data); // 데이터는 response.body 안에 들어있습니다.
      } catch (e) {
        setError(e);
      }
      setLoading(false);
    };

    fetchQustion();
  }, []);

  if (loading) return <div>로딩중..</div>;
  if (error) return <div>에러가 발생했습니다</div>;
  if (!questions) return <div>질문이 없습니다.</div>;


    return (
        <Content>
          <Title>
              <Row1>
                  <H1>All Questions</H1>
                  <Button onClick={handleAskBtnClick}>Ask Question</Button>
              </Row1>
              <Row2>
                  <Em>22,960,505 questions</Em>
                  <Button>Filter</Button>
              </Row2>
          </Title>
          {questions.map(question => (
            <QuestionsList
            key={question.questionId}
            id={question.questionId}
            title={question.questionTitle}
            body={question.questionContent}
            createdAt={question.createdAt}
            author={question.questionWriter}
              />
          ))}
        </Content>
    )
}

export default QuestionsMain

const Content = styled.div`
  width: 100%;
  max-width: 1100px;
`;

const Title = styled.div`
  padding: 24px 24px 0px 24px;
  border: 1px solid #d6d9dc;
  border-left-width: 0;
  border-top-width: 0;
  border-bottom-width: 1px;
  border-right-width: 0;
`;

const Row1 = styled.div`
  display: flex;
  justify-content: space-between;
  height: 50px;
  margin: 0 0 12px;
`;

const H1 = styled.h1`
  font-size: 27px;;
  margin: 0 12px 12px 0;
`;
const Button = styled.button`
  position: relative;
  display: inline-block;
  padding: 10px;
  height: 35px;
  color: #ffffff;
  border: 1px solid #0000;
  border-radius: 3px;
  background-color: #0078D2;
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
`;

const Row2 = styled.div `
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin: 0 0 12px;
  
`
const Em = styled.div`
  font-size: 17px;
`;