import axios from "axios";
import Editor from "./Editor";
import React, { useRef } from "react";
import styled from "styled-components";
import { useParams } from "react-router-dom";

const AnswerPost = () => {
  const answerRef = useRef(null);
  const { QuestionId } = useParams();

  const handleOnChange = () => {
    answerRef.current.getInstance().getMarkdown();
  };

  const handleOnClick = () => {
    if (AnswerPost.body !== "") {
      const headers = {
        "Content-Type": "application/json",
        Authorization: `${localStorage.getItem("authorization")}`,
      };
      const data = {
        answerContent: answerRef.current.getInstance().getMarkdown(),
        questionId: Number(`${QuestionId}`)
      };
      console.log(data)
      axios
        .post(
          `${process.env.REACT_APP_API_URL}/answers`,data,
          {
            headers: headers,
          }
        )
        .then(() => window.location.reload())
        .catch((err) => console.log(err));
    }
  };

  return (
    <Container>
      <PostBody>
      <AnswerPostTitle>
        <div className="title">Your Answer</div>
      </AnswerPostTitle>
        <Editor
          type="write"
          height="300px"
          ref={answerRef}
          onChange={handleOnChange}
        />
        <Button onClick={handleOnClick}>Update your Answer</Button>
      </PostBody>
    </Container>
  );
};

export default AnswerPost;

const Container = styled.div`
  display: flex;
  max-width: 1264px;
  width: 100%;
  border-right-width: 0;
`;

const PostBody = styled.div`
  display: flex;
  flex-direction: column;
  padding: 24px;
`;

const AnswerPostTitle = styled.div`
    font-size: 1.5rem;
    padding-bottom: 20px;
`;

const Button = styled.button`
  width: 154px;
  height: 38px;
  min-height: 38px;
  background-color: #0078d2;
  color: #ffffff;
  border-radius: 3px;
  outline: none;
  border: 1px solid transparent;
  margin: 32px -2px 20px;
  cursor: pointer;
  box-shadow: inset 0 1px 0 0 #fff6;
  &:hover {
    background-color: #0063bf;
  }
`;
