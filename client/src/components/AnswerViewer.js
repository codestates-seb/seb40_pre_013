import React from "react";
import styled from "styled-components";
import { AiFillCaretUp, AiFillCaretDown } from "react-icons/ai";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const AnswerViewer = ({ answerContent, asweruser,answerId }) => {
  const navigate = useNavigate();
  const deleteClick = () => {
    const result = window.confirm("답변을 삭제하시겠습니까?");
    if (result === true) {
      const headers = {
        "Content-Type": "application/json",
        Authorization: `${localStorage.getItem("authorization")}`,
      };
      setTimeout(() => {
        axios
          .delete(`answers/${answerId}`, {
            headers: headers,
          })
          .then(() => window.location.reload())
          .catch((err) => console.log(err));
      }, 1000);
    }
  };

  const editClick = () =>{
    navigate(`/editA/${answerId}`)
  }
  return (
    <Container>
      <AnswerWrap>
        <AnswerTitle>
          <div className="title">Answer</div>
        </AnswerTitle>
        <Contents>
          <Bar>
            <div className="up">
              <AiFillCaretUp />
            </div>
            <div className="down">
              <AiFillCaretDown />
            </div>
          </Bar>
          <AnswerViewerContent>
            <div className="contents">{answerContent}</div>
            <div className="deleteEdit">
              <button onClick={deleteClick}>Delete</button>
              <button onClick={editClick}>Edit</button>
            </div>
            <div className="user">
              <div className="userId">{asweruser}</div>
            </div>
          </AnswerViewerContent>
        </Contents>
      </AnswerWrap>
    </Container>
  );
};

export default AnswerViewer;

const Container = styled.div`
  display: flex;
  max-width: 1264px;
  width: 100%;
  border-right-width: 0;
`;

const AnswerWrap = styled.div`
  width: 100%;
  max-width: 1100px;
  border: 1px solid #d6d9dc;
  border-left-width: 0;
  border-top-width: 0;
  border-bottom-width: 1px;
  border-right-width: 0;
`;
const AnswerTitle = styled.div`
  font-size: 1.5rem;
  padding: 24px;
`;
const Contents = styled.div`
  display: flex;
`;

const Bar = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: baseline;

  div {
    font-size: 21px;
    width: 36px;
    height: 28px;
    display: flex;
    justify-content: center;
    align-items: center;
  }
`;

const AnswerViewerContent = styled.div`
  padding: 20px;
  width: 100%;
  display: flex;
  flex-direction: column;
  gap: 8px 0;

  .contents {
    color: #3b4045;
    font-size: 13px;
    overflow: hidden;
    padding: 0;
    margin: 0;
  }
  .deleteEdit {
    display: flex;
  }
  button {
    display: flex;
    gap: 0px 4px;
    color: hsl(210, 8%, 45%);
    border: none;
    background-color: transparent;
    cursor: pointer;
  }
  .user {
    display: flex;
    justify-content: end;
    align-items: center;
    gap: 0 4px;
  }
  .userId {
    font-size: 12px;
    color: #0074cc;
  }
`;
