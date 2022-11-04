import styled from "styled-components";
import { AiFillCaretUp, AiFillCaretDown } from "react-icons/ai";
import SideBar from "./SideBar";

function QuestionLook() {
  return (
    <Container>
      <Side>
        <SideBar />
      </Side>
      <QuestionWrap>
        <QuestionHeader>
          <h1>제목입니다.</h1>
          <button> Ask Qustion</button>
        </QuestionHeader>
        <QuestionDate>
          <div>Asked today</div>
          <div>Modified today</div>
          <div>Viewed 7 times</div>
        </QuestionDate>
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
            <div className="contents">내용입니다. 내용이 길면 어떻게 나오는지 봐야겠네요</div>
            <div className="deleteEdit">
              <button>Delete</button>
              <button>Edit</button>
            </div>
            <div className="user">
              <div className="userId">유저</div>
            </div>
          </QuestionContents>
        </Contents>
      </QuestionWrap>
    </Container>
  );
}

export default QuestionLook;

const Container = styled.div`
  display: flex;
  max-width: 1264px;
  width: 100%;
  border-right-width: 0;
`;

const Side = styled.div`
  border: 1px solid #d6d9dc;
  border-left-width: 0;
  border-top-width: 0;
  border-bottom-width: 0;
  border-right-width: 1px;
`;

const QuestionWrap = styled.div`
  width: 100%;
  max-width: 1100px;
  border: 1px solid #d6d9dc;
  border-left-width: 0;
  border-top-width: 0;
  border-bottom-width: 1px;
  border-right-width: 0;
  padding: 24px;
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
