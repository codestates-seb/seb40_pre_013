import styled from "styled-components"
import { ReactComponent as EarthLogo } from '../components/Icons/earth.svg';
import { ReactComponent as UpLogo } from '../components/Icons/업화살표.svg';
import { ReactComponent as DownLogo } from '../components/Icons/다운화살표.svg';
import { ReactComponent as SmallBookMarkLogo } from '../components/Icons/북마크스몰.svg';
import { ReactComponent as TimerLogo } from '../components/Icons/타이머.svg';
import SideBar from "./SideBar";


const QuestionLookup = () => {
    return (
        <QuestionLookupStyle>
            <div className="container">
                <Side>
                    <SideBar />
                </Side>
                <div id="content" className="snippet-hidden">
                    <div className="inner-content clearfix">
                        <div id="question-header" class="d-flex sm:fd-column">
                            <h1 itemprop="name" class="fs-headline1 ow-break-word mb8 flex--item fl1"><a href="#" class="question-hyperlink">Angular innerhtml element failed in capturing click event</a></h1>
                            <div class="ml12 aside-cta flex--item print:d-none sm:ml0 sm:mb12 sm:order-first sm:as-end">
                                <a href="#" class="ws-nowrap s-btn s-btn__primary">
                                    Ask Question
                                </a>
                            </div>
                        </div>
                        <div class="d-flex fw-wrap pb8 mb16 bb bc-black-075">
                            <div class="flex--item ws-nowrap mr16 mb8" title="2022-11-01 03:57:36Z">
                                <span class="fc-light mr2">Asked</span>
                                <time itemprop="dateCreated" datetime="2022-11-01T03:57:36">today</time>
                            </div>
                            <div class="flex--item ws-nowrap mr16 mb8">
                                <span class="fc-light mr2">Modified</span>
                                <a href="?lastactivity" class="s-link s-link__inherit" title="2022-11-01 06:11:52Z">today</a>
                            </div>
                            <div class="flex--item ws-nowrap mb8" title="Viewed 21 times">
                                <span class="fc-light mr2">Viewed</span>
                                21 times
                            </div>
                        </div>
                        <div className="post-layout">
                            <div class="votecell post-layout--left">
                                <div class="js-voting-container d-flex jc-center fd-column ai-stretch gs4 fc-black-200" data-post-id="74271381">
                                    <button class="js-vote-up-btn flex--item s-btn s-btn__unset c-pointer " data-controller="s-tooltip" data-s-tooltip-placement="right" aria-pressed="false" aria-label="Up vote" data-selected-classes="fc-theme-primary" data-unselected-classes="" aria-describedby="--stacks-s-tooltip-dgg8uy0v">
                                        <UpLogo />
                                    </button><div id="--stacks-s-tooltip-dgg8uy0v" class="s-popover s-popover__tooltip" role="tooltip">This question shows research effort; it is useful and clear<div class="s-popover--arrow"></div></div>
                                    <div class="js-vote-count flex--item d-flex fd-column ai-center fc-black-500 fs-title" itemprop="upvoteCount" data-value="0">
                                        0
                                    </div>
                                    <button class="js-vote-down-btn flex--item s-btn s-btn__unset c-pointer " data-controller="s-tooltip" data-s-tooltip-placement="right" aria-pressed="false" aria-label="Down vote" data-selected-classes="fc-theme-primary" data-unselected-classes="" aria-describedby="--stacks-s-tooltip-1ufrjweh">
                                        <DownLogo/>
                                    </button><div id="--stacks-s-tooltip-1ufrjweh" class="s-popover s-popover__tooltip" role="tooltip">This question does not show any research effort; it is unclear or not useful<div class="s-popover--arrow"></div></div>
                                    <button class="js-saves-btn s-btn s-btn__unset c-pointer py4" id="saves-btn-74271381" data-controller="s-tooltip" data-s-tooltip-placement="right" data-s-popover-placement="" aria-pressed="false" data-post-id="74271381" data-post-type-id="1" data-user-privilege-for-post-click="-1" aria-controls="" data-s-popover-auto-show="false" aria-describedby="--stacks-s-tooltip-d3rvm98c">
                                        <SmallBookMarkLogo/>
                                    </button><div id="--stacks-s-tooltip-d3rvm98c" class="s-popover s-popover__tooltip" role="tooltip">Save this question.<div class="s-popover--arrow"></div></div>
                                    <a class="js-post-issue flex--item s-btn s-btn__unset c-pointer py6 mx-auto" href="/posts/74271381/timeline" data-shortcut="T" data-ks-title="timeline" data-controller="s-tooltip" data-s-tooltip-placement="right" aria-label="Timeline" aria-describedby="--stacks-s-tooltip-zzfrikcn"><TimerLogo/></a><div id="--stacks-s-tooltip-zzfrikcn" class="s-popover s-popover__tooltip" role="tooltip">Show activity on this post.<div class="s-popover--arrow"></div></div>
                                </div>
                            <div className="d-flex gs8 s-anchors s-anchors__muted fw-wrap">
                                <div className="flex--item"><a href="#">Delete</a></div>
                                <div className="flex--item"><a href="#">Edit</a></div>
                            </div>
                        </div>
                        
                        
                        

                        </div>
                    </div>
                </div>



            </div>
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
        float: right;
    }
    .nav-links--link {
        color: black;
    }
    .question-hyperlink {
        color:black;
        font-weight:normal;
    }
    `
    const Side = styled.div`
    border: 1px solid #d6d9dc;
    border-left-width: 0;
    border-top-width: 0;
    border-bottom-width: 0;
    border-right-width: 1px;
  `;

export default QuestionLookup