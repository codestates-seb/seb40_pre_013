import styled from "styled-components";
import SideBar from "../components/SideBar"
import QuestinsTitle from "../components/QuestionsTitle"
import QuestinsList from "../components/QuestionsList"


function Main() {
    return (
      <>
      <Container>
        <side>
          <SideBar/>
        </side>
        <Contents>
          <QuestinsTitle/>
          <QuestinsList/>
        </Contents>
      </Container>
      </>
    );
  }
  
export default Main

const Container = styled.div`
  display: flex;
  max-width: 1288px;
`

const Contents = styled.div`
  width : 100%;
  border: 1px solid #d6d9dc;
  border-left-width: 1px;
  border-top-width: 0;
  border-bottom-width: 0;
  border-right-width: 0;
`