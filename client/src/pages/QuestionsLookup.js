import styled from "styled-components";
import SideBar from "../components/SideBar";
import QuestionsVeiwTitle from "../components/QuestionVeiwTitle";
import QuestionsViewContent from "../components/QuestionViewContent";
import AnswerViewer from "../components/AnswerViewer";
import { useParams } from "react-router-dom";
import React, { useState, useEffect } from "react";
import axios from "axios";
import AnswerPost from "../components/Answerpost";

function Main() {
  const { QuestionId } = useParams();
  const [questions, setQuestions] = useState(null);
  const [answer, setAnswer] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  useEffect(() => {
    const fetchQustion = async () => {
      try {
        // 요청이 시작 할 때에는 error 와 questions 를 초기화하고
        setError(null);
        setQuestions(null);
        // loading 상태를 true 로 바꿉니다.
        setLoading(true);
        const response = await axios.get(`/questions/${QuestionId}`, {
          headers: {
            "ngrok-skip-browser-warning": "skip",
          },
        });
        console.log(response);
        setQuestions(response.data.questionResponse);
        setAnswer(response.data.answerData);
        console.log(response.data.answerData); // 데이터는 response.body 안에 들어있습니다.
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
    <>
      <Container>
        <Side>
          <SideBar />
        </Side>
        <ContentContainer>
          {questions.length !== 0 && (
            <>
              <QuestionsVeiwTitle
                key={questions.questionId}
                title={questions.questionTitle}
                time={questions.createdAt}
                modifie={questions.modifiedAt}
              />
              <Content>
                <QuestionsViewContent
                  key={questions.questionId}
                  id={questions.questionId}
                  title={questions.questionTitle}
                  content={questions.questionContent}
                  user={questions.questionWriter}
                  questionId={questions.questionId}
                  memberId={questions.memberId}
                />
              </Content>
            </>
          )}
          {answer.map((el) => (
            <AnswerViewer
              key={el.answerId}
              answerId={el.answerId}
              asweruser={el.writer}
              answerContent={el.answerContent}
              answerTime={el.modifiedAt}
            />
          ))}
          <AnswerPost />
        </ContentContainer>
      </Container>
    </>
  );
}

export default Main;

const Container = styled.div`
  margin: 0 auto;
  display: flex;
  justify-content: space-between;
  max-width: 1264px;
  width: 100%;
`;
const Side = styled.div`
  border: 1px solid #d6d9dc;
  border-left-width: 0;
  border-top-width: 0;
  border-bottom-width: 0;
  border-right-width: 1px;
`;
const ContentContainer = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  padding: 24px;
`;
const Content = styled.div`
  width: 100%;
`;
