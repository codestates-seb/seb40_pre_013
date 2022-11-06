
import { Link } from "react-router-dom";
import styled from "styled-components";

function QuestionsList({ id, author, title, body, createdAt }) {


  return (
    <Container>
      <Stats>
        <div>0 votes</div>
        <div>0 answer</div>
        <div>0 views</div>
      </Stats>

      <QuestionContent>
        <Link to={`/questions/${id}`} className ="title" >{title}</Link>
        <div className="contents">{body}</div>
        <div className="User">
          <div className="userID">{author}</div>
          <div className="timeAgo">{createdAt} </div>
        </div>
      </QuestionContent>
    </Container>
  );
}

export default QuestionsList;

const Container = styled.main`
  max-width: 1052px;
  display: flex;
  gap: 0 16px;
  border-bottom: 1px solid #e3e6e8;
  padding: 24px;
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
`;

const QuestionContent = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  gap: 8px 0;

  .title {
    color: #0078d2;
    word-break: break-word;
    hyphens: auto;
    font-size: 17px;
    padding: 0;
    margin: 0;
    cursor: pointer;
  }

  .contents {
    color: #3b4045;
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
`;
