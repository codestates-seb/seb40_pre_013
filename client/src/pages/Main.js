import styled from "styled-components";
import SideBar from "../components/SideBar"
import QuestionsTitle from "../components/QuestionsTitle"
import Pagination from '../components/Pagination';

function Main() {
    return (
      <>
      <Container>
        <Side>
          <SideBar/>
        </Side>
        <Content>
          <QuestionsTitle/>
          <Pagination/>
        </Content>
      </Container>
      </>
    );
  }
  
export default Main

const Container = styled.div`
  margin: 0 auto;
  display: flex;
  justify-content: space-between;
  max-width: 1264px;
  width: 100%;
`
const Side = styled.div`
  border: 1px solid #d6d9dc;
  border-left-width: 0;
  border-top-width: 0;
  border-bottom-width: 0;
  border-right-width: 1px;
`;
const Content = styled.div`
  width: 100%;
`;