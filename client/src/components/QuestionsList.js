
import { Link } from "react-router-dom";
import styled from "styled-components";

function QuestionsList({ id, author, title, body, createdAt }) {
  // 로그인을 했을 경우에만 Ask question 눌렀을때 질문 작성이 가능 그게 아니라면 로그인 화면으로 이동 
  const handleTitleClick = () => {
    if(sessionStorage.getItem('username') !== null) {
      window.location.href = '/qlookup';
    }
    else {
      alert('로그인을 먼저 하고 오세요!');
      window.location.href = '/login';
    }
  };


  return (
    <Container>
      <Stats>
        <div>0 votes</div>
        <div>0 answer</div>
        <div>0 views</div>
      </Stats>

      <QuestionContent>
        <Link to={`/questions/${id}`} className ="title" onClick={handleTitleClick}>{title}</Link>
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
