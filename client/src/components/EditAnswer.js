import styled from "styled-components";
import Editor from "./Editor";
import SideBar from "./SideBar"

function EditAnswer() {
    return (
      <Container>
        <Side>
          <SideBar/>
        </Side>
        <Content>
            <ContentHeader>
                <h1>Edit Answer</h1>
            </ContentHeader>
            <Post>
                <PostBody>
                  <h2>Body</h2>
                  <Editor />
                  <BlueButton>Save Edits</BlueButton>
                  <Button>Cancel</Button>
                </PostBody>
            </Post>
        </Content>
      </Container>
    );
  }
  
  export default EditAnswer

const Side = styled.div`
  border: 1px solid #d6d9dc;
  border-left-width: 0;
  border-top-width: 0;
  border-bottom-width: 0;
  border-right-width: 1px;
`;

const Container = styled.div`
  display: flex;
  width: 100%;
  max-width: 1264px;
  margin: 0 auto;
`;

const Content = styled.div`
  display: flex;
  flex-direction: column;
  margin: 16px;
  justify-content: center;
  width: 100%;
`;

const ContentHeader = styled.div`
  display: flex;
  padding: 24px 0px;
  width: 100%;

h1 {
  font-size: 31px;
  color: #232629;
}
`;

const Post = styled.div`
  display: flex;
  flex-direction: column;
  box-shadow: 0 1px 3px hsla(0, 0%, 0%, 0.06), 0 2px 6px hsla(0, 0%, 0%, 0.06),
    0 3px 8px hsla(0, 0%, 0%, 0.09);
  border-radius: 3px;
  width: 100%;
`;


const PostBody = styled.div`
  padding: 24px;

  h2{
    color: #0c0d0e;
    font-size: 15px;
    margin: 2px 0;
    padding: 0 2px;
  }
`;

const BlueButton = styled.button`
  width: 100px;
  height: 38px;
  min-height: 38px;
  background-color: #0a95ff;
  color: #ffffff;
  border-radius: 3px;
  outline: none;
  border: 1px solid transparent;
  margin: 32px -2px 0px;
  cursor: pointer;
`;

const Button = styled.button`
  width: 100px;
  height: 38px;
  min-height: 38px;
  background-color: #ffffff;
  color: #0078D2;
  box-shadow: inset 0 1px 0 0 #fff6;
  border-radius: 3px;
  outline: none;
  border: 1px solid #d6d9dc;
  padding: 10px;
  margin-left: 10px;
  cursor: pointer;
`;