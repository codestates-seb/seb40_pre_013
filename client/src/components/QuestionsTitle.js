import styled from "styled-components";
import { useNavigate } from "react-router-dom";
import QuestionsList from "./QuestionsList";
import axios from "axios";
import React, { useState, useEffect } from "react";
import Pagination from "react-js-pagination";

function QuestionsMain() {
  const [questions, setQuestions] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [page, setPage] = useState(1);
  const [items, setItems] = useState(5);

  const handlePageChange = (page) => {
    setPage(page);
  };

  const navigate = useNavigate();
  const handleAskBtnClick = () => {
    navigate("/ask");
  };

  useEffect(() => {
    const fetchQustion = async () => {
      try {
        // 요청이 시작 할 때에는 error 와 questions 를 초기화하고
        setError(null);
        setQuestions(null);
        // loading 상태를 true 로 바꿉니다.
        setLoading(true);
        const response = await axios.get(
          "https://f78a-36-38-67-6.jp.ngrok.io/questions/",
          {
            headers: {
              "ngrok-skip-browser-warning": "skip",
            },
          }
        );
        console.log(response);
        setQuestions(response.data); // 데이터는 response.body 안에 들어있습니다.
      } catch (e) {
        setError(e);
      }
      setLoading(false);
    };

    fetchQustion();
  }, []);
  const itemChange = (e) => {
    setItems(Number(e.target.value));
  };
  if (loading) return <div>로딩중..</div>;
  if (error) return <div>에러가 발생했습니다</div>;
  if (!questions) return <div>질문이 없습니다.</div>;

  return (
    <Content>
      <Title>
        <Row1>
          <H1>All Questions</H1>
          <Button onClick={handleAskBtnClick}>Ask Question</Button>
        </Row1>
        <Row2>
          <Em>22,960,505 questions</Em>
          <Button>Filter</Button>
        </Row2>
      </Title>
      {questions
        .slice(items * (page - 1), items * (page - 1) + items)
        .map((question) => (
          <QuestionsList
            key={question.questionId}
            id={question.questionId}
            title={question.questionTitle}
            body={question.questionContent}
            createdAt={question.createdAt}
            author={question.questionWriter}
          />
        ))}
      <PaginationBox>
        <PaginationSelect onClick={itemChange}>
          <option>5</option>
          <option>10</option>
          <option>15</option>
        </PaginationSelect>
        <Pagination
          activePage={page}
          itemsCountPerPage={items}
          totalItemsCount={questions.length}
          pageRangeDisplayed={5}
          prevPageText={"‹"}
          nextPageText={"›"}
          onChange={handlePageChange}
        />
      </PaginationBox>
    </Content>
  );
}

export default QuestionsMain;

const Content = styled.div`
  width: 100%;
  max-width: 1100px;
`;

const Title = styled.div`
  padding: 24px 24px 0px 24px;
  border: 1px solid #d6d9dc;
  border-left-width: 0;
  border-top-width: 0;
  border-bottom-width: 1px;
  border-right-width: 0;
`;

const Row1 = styled.div`
  display: flex;
  justify-content: space-between;
  height: 50px;
  margin: 0 0 12px;
`;

const H1 = styled.h1`
  font-size: 27px;
  margin: 0 12px 12px 0;
`;
const Button = styled.button`
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
`;

const Row2 = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin: 0 0 12px;
`;
const Em = styled.div`
  font-size: 17px;
`;
const PaginationSelect = styled.div`
  display: flex;
  justify-content: right;
  margin: 0px 0px;
  padding: 24px 24px 0 0;
  cursor: pointer;
  option {
    display: inline-block;
    width: 40px;
    height: 30px;
    border: 1px solid #e2e2e2;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 13px;
    border-radius: 3px;
    margin-left: 2px;
    margin-right: 2px;
  }
  option:hover {
    background-color: hsl(210, 8%, 95%);
  }
  option:active a {
    color: white;
  }
  option:active, option:focus{
    background-color: hsl(27, 90%, 55%);
    border-color: transparent !important;
  }
  
`;
const PaginationBox = styled.div`
  .pagination {
    display: flex;
    justify-content: left;
    margin: 20px 0px;
    padding: 0 24px 24px 24px;
  }
  ul {
    list-style: none;
    padding: 0;
  }
  ul.pagination li {
    display: inline-block;
    width: 30px;
    height: 30px;
    border: 1px solid #e2e2e2;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 13px;
    border-radius: 3px;
    margin-left: 2px;
    margin-right: 2px;
  }
  ul.pagination li a {
    color: hsl(210, 8%, 25%);
  }
  ul.pagination li:hover {
    background-color: hsl(210, 8%, 95%);
  }
  ul.pagination li.active a {
    color: white;
  }
  ul.pagination li.active {
    background-color: hsl(27, 90%, 55%);
    border-color: transparent !important;
  }
`;
