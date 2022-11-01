import React, { useState } from "react";
import Pagination from "react-js-pagination";
import styled from "styled-components";

const Paging = () => {
  const [page, setPage] = useState(1);

  const handlePageChange = (page) => {
    setPage(page);
  };

  return (
    <PaginationBox>
    <Pagination
      activePage={page}
      itemsCountPerPage={10}
      totalItemsCount={450}
      pageRangeDisplayed={5}
      prevPageText={"‹"}
      nextPageText={"›"}
      onChange={handlePageChange}
    />
    </PaginationBox>
  );
};

export default Paging;

const PaginationBox = styled.div`
  .pagination { 
    display: flex; 
    justify-content: left; 
    margin: 20px 0px;
    padding: 24px;
  }
  ul { 
    list-style: 
    none; padding: 0; 
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
    color: hsl(210,8%,25%);
  }

  ul.pagination li.active a { 
    color: white; 
  }
  ul.pagination li.active { 
    background-color: hsl(27,90%,55%); 
  }
  ul.pagination li:hover {
   background-color: hsl(210,8%,95%);
  }
`