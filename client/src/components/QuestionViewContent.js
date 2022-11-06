import React from "react";
import styled from "styled-components";
import { AiFillCaretUp, AiFillCaretDown } from "react-icons/ai";

const QuestionsViewContent = ({content,user}) => {
      return (
        <Container>
          <QuestionWrap>
            <Contents>
              <Bar>
                <div className="up">
                  <AiFillCaretUp />
                </div>
                <div className="down">
                  <AiFillCaretDown />
                </div>
              </Bar>
              <QuestionContents>
                <div className="contents">{content}</div>
          
                <div className="deleteEdit">
                  <button>Delete</button>
                  <button>Edit</button>
                </div>
                <div className="user">
                  <div className="userId">{user}</div>
                </div>
              </QuestionContents>
            </Contents>
          </QuestionWrap>
        </Container>
      );
};

export default QuestionsViewContent ;

const Container = styled.div`
  display: flex;
  max-width: 1264px;
  width: 100%;
  border-right-width: 0;
`;

const QuestionWrap = styled.div`
  width: 100%;
  max-width: 1100px;
  border: 1px solid #d6d9dc;
  border-left-width: 0;
  border-top-width: 0;
  border-bottom-width: 1px;
  border-right-width: 0;
`;

const Contents = styled.div`
  display: flex;
`;

const Bar = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: baseline;
  padding: 16px;
  div {
    font-size: 21px;
    width: 36px;
    height: 28px;
    display: flex;
    justify-content: center;
    align-items: center;
  }
`;

const QuestionContents = styled.div`
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
