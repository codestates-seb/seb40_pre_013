import styled from "styled-components"
import SideBar from "./SideBar";
import QuestionResponse from "./questionResponse";
import { Link } from "react-router-dom";
import { Editor } from "@toast-ui/react-editor";

const QuestionLookup = () => {
    return (
        <QuestionLookupStyle>
            <Container>
                <Side>
                    <SideBar />
                </Side>
                <div className="snippet-hidden">
                    <div className="inner-content clearfix">
                        <div id="question-header" class="d-flex sm:fd-column">
                            <h1 className="fs-headline1 ow-break-word mb8 flex--item fl1"><a href="#" class="question-hyperlink">Angular innerhtml element failed in capturing click event</a></h1>
                            <div className="ml12 aside-cta flex--item print:d-none sm:ml0 sm:mb12 sm:order-first sm:as-end">
                                <Link to='/ask' className="ws-nowrap s-btn s-btn__primary">
                                    Ask Question
                                </Link>
                            </div>
                        </div>
                        <div className="d-flex fw-wrap pb8 mb16 bb bc-black-075">
                            <div className="flex--item ws-nowrap mr16 mb8" title="2022-11-01 03:57:36Z">
                                <span className="fc-light mr2">Asked</span>
                                <time itemprop="dateCreated" datetime="2022-11-01T03:57:36">today</time>
                            </div>
                            <div className="flex--item ws-nowrap mr16 mb8">
                                <span className="fc-light mr2">Modified</span>
                                <a href="?lastactivity" className="s-link s-link__inherit" title="2022-11-01 06:11:52Z">today</a>
                            </div>
                            <div className="flex--item ws-nowrap mb8" title="Viewed 21 times">
                                <span className="fc-light mr2">Viewed</span>
                                21 times
                            </div>
                        </div>
                        <QuestionResponse />
                    </div>
                    <div className="response">
                        <h2>2Answers</h2>
                        <QuestionResponse />
                    </div>
                    <div className="editor">
                        <h2 className="space">Your Answer</h2>
                        <Editor />
                        <Button >Post your Answer</Button>
                    </div>
                </div>
                
            </Container>

        </QuestionLookupStyle>
    )
}

const QuestionLookupStyle = styled.div`
    .sidebar {
        list-style:none;
    }    
    .container {
        margin-right:35px;
        margin-left: 35px;
    }
    .ps-relative {
        display: inline-block;
    }
    .snippet-hidden {
        padding: 24px 16px;
    }
    .nav-links--link {
        color: black;
    }
    .question-hyperlink {
        color:black;
        font-weight:normal;
    }
    .post-layout--left {
        float : left;
        color: lightgray;
    }
    .votecell {
        padding-right: 16px;
    }
    .fs-headline1 {
        padding-right:150px;
    }
    .response {
        margin-top:30px;
    }
    .editor {
        position: absolute;
        bottom: -100px;
    }
    `
const Side = styled.div`
    border: 1px solid #d6d9dc;
    border-left-width: 0;
    border-top-width: 0;
    border-bottom-width: 0;
    border-right-width: 1px;
`
const Container = styled.div`
  margin: 0 auto;
  display: flex;
  max-width: 1264px;
  width: 100%;
`
const Button = styled.button`
  width: 154px;
  height: 38px;
  min-height: 38px;
  background-color: #0a95ff;
  color: #ffffff;
  border-radius: 3px;
  outline: none;
  border: 1px solid transparent;
  margin: 32px -2px 20px;
  cursor: pointer;
`;

export default QuestionLookup