import styled from "styled-components";
import Editor from "./Editor";
import SideBar from "./SideBar";
import axios from "axios";
import React, { useRef } from "react";
import { useParams } from "react-router-dom";
import { useNavigate } from "react-router-dom";

function EditAnswer() {
  const navigate = useNavigate();
  const { QuestionId } = useParams();
  const answerRef = useRef(null);

  const saveClick = () => {
    const data = {
      questionContent: answerRef.current.getInstance().getMarkdown(),
    }
    const headers = {
      "Content-Type": "application/json",
      Authorization: `${localStorage.getItem("authorization")}`,
    };
    axios
      .patch(
        `/answers/${QuestionId}`,
        data,
        { headers: headers }
      )
      .then(() => navigate(`/answer/${QuestionId}`))
  };

  const cancleClick = () => {
    navigate(`/answer/${QuestionId}`);
  };
  return (
    <Container>
      <Side>
        <SideBar />
      </Side>
      <Content>
        <ContentHeader>
          <h1>Edit Answer</h1>
        </ContentHeader>
        <Post>
          <PostBody>
            <h2>Body</h2>
            <Editor
              type="write"
              height="300px"
            />
            <BlueButton onClick={saveClick}>Save Edits</BlueButton>
            <Button onClick={cancleClick}>Cancel</Button>
          </PostBody>
        </Post>
      </Content>
    </Container>
  );
}

export default EditAnswer;

const Side = styled.div`
  border: 1px solid #d6d9dc;
  border-left-width: 0;
  border-top-width: 0;
  border-bottom-width: 0;
  border-right-width: 1px;
`;

const Container = styled.div`
  display: flex;
  width: 100%;
  margin: 0 auto;
  max-width: 1264px;
`;

const Content = styled.div`
  display: flex;
  flex-direction: column;
  margin: 16px;
  justify-content: center;
  width: 100%;
`;

const ContentHeader = styled.div`
  display: flex;
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
  box-shadow: 0 1px 3px hsla(0, 0%, 0%, 0.06), 0 2px 6px hsla(0, 0%, 0%, 0.06),
    0 3px 8px hsla(0, 0%, 0%, 0.09);
  border-radius: 3px;
  width: 100%;
`;

const PostBody = styled.div`
  padding: 24px;

  h2 {
    color: #0c0d0e;
    font-size: 15px;
    margin: 2px 0;
    padding: 0 2px;
  }
`;

const BlueButton = styled.button`
  width: 100px;
  height: 38px;
  min-height: 38px;
  background-color: #0078d2;
  box-shadow: inset 0 1px 0 0 #fff6;
  color: #ffffff;
  border-radius: 3px;
  outline: none;
  border: 1px solid transparent;
  margin: 32px -2px 0px;
  cursor: pointer;
  &:hover {
    background-color: #0063bf;
  }
`;

const Button = styled.button`
  width: 100px;
  height: 38px;
  min-height: 38px;
  background-color: #ffffff;
  color: #0078d2;
  box-shadow: inset 0 1px 0 0 #fff6;
  border-radius: 3px;
  outline: none;
  border: 1px solid #d6d9dc;
  padding: 10px;
  margin-left: 10px;
  cursor: pointer;
  &:hover {
    background-color: hsl(210, 8%, 90%);
  }
`;
