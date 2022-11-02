import { Link } from "react-router-dom";
import styled from "styled-components";

function QuestionsList() {
    return (
        <Container>
            <Stats>
                <div>0 votes</div>
                <div>0 answer</div>
                <div>0 views</div>
            </Stats>
            <QuestionContent>
                <h2><Link to='/qlookup'>제목입니다.</Link></h2>
                <contents>내용입니다.</contents>
                <div className= "User">
                    <div className="userID">yumi</div>
                    <div className="timeAgo">asked 1 min ago </div>
                </div>
            </QuestionContent>
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

  contents{
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
