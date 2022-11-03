import { useNavigate } from "react-router-dom";
import React, { useState, useEffect } from 'react';
import styled from "styled-components";
import axios from 'axios'

function QuestionsList() {

  const [questions, setQuestions] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  const handleTitleClick = () => {
  navigate("/qlookup");
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
          'https://16dc-114-204-149-224.jp.ngrok.io/', {
            headers:{
              "ngrok-skip-browser-warning": "skip"
            }
          }
        )
        console.log(response)
        setQuestions(response.body); // 데이터는 response.body 안에 들어있습니다.
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
        <Container>
            <Stats>
                <div>0 votes</div>
                <div>0 answer</div>
                <div>0 views</div>
            </Stats>
            {questions.map(question => (
                <QuestionContent>
                   <h2 key={question.questionId} onClick={handleTitleClick}>{question.questionTitle}</h2>
                   <div key={question.questionId} className="contents">{question.questionContent}</div>
                   <div key={question.questionId} className= "User">
                   <div key={question.questionId} className="userID">{question.memberId}</div>
                   <div key={question.questionId} className="timeAgo">{question.createdAt} </div>
                </div>
                </QuestionContent>
            ))}


        </Container>
    )
}

export default QuestionsList

const Container = styled.main`
  max-width: 1052px;
  display: flex;
  gap: 0 16px;
  border-bottom: 1px solid #E3E6E8;
  padding: 24px;
  cursor: pointer;
`;


const Stats = styled.div`
  width: 92px;
  display: flex;
  flex-direction: column;
  flex-shrink: 0;
  flex-wrap: wrap;
  align-items: flex-end;
  gap: 4px 16px;

  div {
    color: #6a737c;
    font-size: 13px;
  }
`

const QuestionContent = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  gap: 8px 0;

  h2{
  color: #0078D2;
  word-break: break-word;
  hyphens: auto;
  font-size: 17px;
  padding: 0;
  margin: 0;
  }

  .contents {
  color: #3B4045;
  font-size: 13px;
  overflow: hidden;
  padding: 0;
  margin: 0;
  }

  .User {
  display: flex;
  justify-content: end;
  align-items: center;
  gap: 0 4px;
  }
  .userId {
  font-size: 12px;
  color: #0074cc;
  }
  .timeAgo {
  font-size: 12px;
  color: #6a737c;
  }
`