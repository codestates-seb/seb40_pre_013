import styled from "styled-components";
import Editor from "./Editor";
import React, { useRef } from "react";
import { useNavigate } from "react-router-dom";
import { useParams } from "react-router-dom";
import axios from "axios";

function AskQuestion() {
  const navigate = useNavigate();
  const titleRef = useRef(null);
  const editorRef = useRef(null);

  const handleOnChange = () => {
    editorRef.current.getInstance().getMarkdown();
  };

  const handleOnClick = () => {
    const data = {
      questionTitle: titleRef.current.value,
      questionContent: editorRef.current.getInstance().getMarkdown(),
    };
    const headers = {
      "Content-Type": "application/json",
      "Authorization":
        `${localStorage.getItem("authorization")}`,
    };


    console.log(data);
    console.log(headers)
    axios
      .post("/questions", data, {
        headers: headers,
      })

      .then((res) => navigate(`/questions/${res.data.questionId}`))
      .catch((err) => console.log(err));
  };

  return (
    <Container>
      <Content>
        <ContentHeader>
          <h1>Ask a public question</h1>
        </ContentHeader>
        <Post>
          <PostTitle>
            <h2>Title</h2>
            <p>
              Be specific and imagine you’re asking a question to another
              person.
            </p>
            <input
              type="text"
              placeholder="e.g is there an R function someone would need to answer your question"
              ref={titleRef}
            ></input>
          </PostTitle>
          <PostBody>
            <h2>Body</h2>
            <p>
              Include all the information someone would need to answer you
              question
            </p>
            <Editor
              type="write"
              height="300px"
              ref={editorRef}
              onChange={handleOnChange}
            />
            <Button onClick={handleOnClick}>Update your question</Button>
          </PostBody>
        </Post>
      </Content>
    </Container>
  );
}

export default AskQuestion;

const Container = styled.div`
  background-color: hsl(210, 8%, 95%);
  min-height: calc(100vh - 50px);
  width: 100%;
`;

const Content = styled.div`
  margin: 0 auto;
  max-width: 1264px;
  min-height: 550px;
  display: flex;
  flex-direction: column;
  padding: 16px;
  justify-content: center;
`;

const ContentHeader = styled.div`
  display: flex;
  flex-direction: column;
  padding: 24px 0px;
  width: 100%;

  h1 {
    font-size: 31px;
    color: #232629;
  }
`;

const Post = styled.div`
  display: flex;
  flex-direction: column;
  background-color: hsl(0, 0%, 100%);
  box-shadow: 0 1px 3px hsla(0, 0%, 0%, 0.06), 0 2px 6px hsla(0, 0%, 0%, 0.06),
    0 3px 8px hsla(0, 0%, 0%, 0.09);
  border-radius: 3px;
  width: 100%;
`;

const PostTitle = styled.div`
  display: flex;
  flex-direction: column;
  padding: 24px;

  h2 {
    color: #0c0d0e;
    font-size: 15px;
    margin: 0;
  }

  p {
    color: #0c0d0e;
    font-size: 12px;
    margin: 2px 0;
    padding: 0 2px;
  }
  input {
    width: 97.5%;
    margin: 0;
    padding: 7px 9px;
  }
`;

const PostBody = styled.div`
  display: flex;
  flex-direction: column;
  padding: 24px;

  h2 {
    color: #0c0d0e;
    font-size: 15px;
    margin: 0;
  }

  p {
    color: #0c0d0e;
    font-size: 12px;
    margin: 2px 0;
    padding: 0 2px;
  }
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
