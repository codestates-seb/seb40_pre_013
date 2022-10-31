import styled from "styled-components";

function QuestionsMain() {
    return (
        <Content>
          <Title>
              <Row1>
                  <H1>All Questions</H1>
                  <Button>Ask Question</Button>
              </Row1>
              <Row2>
                  <Em>22,960,505 questions</Em>
                  <Button>Filter</Button>
              </Row2>
          </Title>
        </Content>
    )
}

export default QuestionsMain

const Content = styled.div`
  width: 100%;
  max-width: 1100px;
  border: 1px solid #d6d9dc;
  border-left-width: 0;
  border-top-width: 0;
  border-bottom-width: 1px;
  border-right-width: 0;
`;

const Title = styled.div`
  padding: 24px 24px 0px 24px;
`;

const Row1 = styled.div`
  display: flex;
  justify-content: space-between;
  height: 50px;
  margin: 0 0 12px;
`;

const H1 = styled.h1`
  font-size: 27px;;
  margin: 0 12px 12px 0;
`;
const Button = styled.button`
  position: relative;
  display: inline-block;
  padding: 10px;
  height: 35px;
  color: #ffffff;
  border: 1px solid ;
  border-radius: 3px;
  background-color: #0078D2;
  outline: none;
  font-size: 13px;
  font-weight: normal;
  text-align: center;
  text-decoration: none;
  cursor: pointer;
  user-select: none;
`;

const Row2 = styled.div `
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin: 0 0 12px;
  
`
const Em = styled.div`
  font-size: 17px;
`;