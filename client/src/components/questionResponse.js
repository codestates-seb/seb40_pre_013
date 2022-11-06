import { ReactComponent as UpLogo } from '../components/Icons/업화살표.svg';
import { ReactComponent as DownLogo } from '../components/Icons/다운화살표.svg';
import { ReactComponent as SmallBookMarkLogo } from '../components/Icons/북마크스몰.svg';
import { ReactComponent as TimerLogo } from '../components/Icons/타이머.svg';
import { useNavigate } from "react-router-dom";


const QuestionResponse = ({body}) => {
    const navigate = useNavigate();
    const handleEditQbtnClick = () => {
        navigate("/editQ");
        }
    
    return (
        <div className="post-layout">
            <div className="votecell post-layout--left">
                <div className="js-voting-container d-flex jc-center fd-column ai-stretch gs4 fc-black-200" >
                    <button className="js-vote-up-btn flex--item s-btn s-btn__unset c-pointer " data-controller="s-tooltip" data-s-tooltip-placement="right" aria-pressed="false" aria-label="Up vote" data-selected-classes="fc-theme-primary" data-unselected-classes="" aria-describedby="--stacks-s-tooltip-dgg8uy0v">
                        <UpLogo />
                    </button><div id="--stacks-s-tooltip-dgg8uy0v" className="s-popover s-popover__tooltip" role="tooltip">This question shows research effort; it is useful and clear<div className="s-popover--arrow"></div></div>
                    <div className="js-vote-count flex--item d-flex fd-column ai-center fc-black-500 fs-title" data-value="0">0</div>
                    <button className="js-vote-down-btn flex--item s-btn s-btn__unset c-pointer " data-controller="s-tooltip" data-s-tooltip-placement="right" aria-pressed="false" aria-label="Down vote" data-selected-classes="fc-theme-primary" data-unselected-classes="" aria-describedby="--stacks-s-tooltip-1ufrjweh">
                        <DownLogo /></button>
                    <div id="--stacks-s-tooltip-1ufrjweh" className="s-popover s-popover__tooltip" role="tooltip">This question does not show any research effort; it is unclear or not useful<div className="s-popover--arrow"></div></div>
                    <button className="js-saves-btn s-btn s-btn__unset c-pointer py4" id="saves-btn-74271381" data-controller="s-tooltip" data-s-tooltip-placement="right" data-s-popover-placement="" aria-pressed="false" data-post-id="74271381" data-post-type-id="1" data-user-privilege-for-post-click="-1" aria-controls="" data-s-popover-auto-show="false" aria-describedby="--stacks-s-tooltip-d3rvm98c">
                        <SmallBookMarkLogo /></button>
                    <div id="--stacks-s-tooltip-d3rvm98c" className="s-popover s-popover__tooltip" role="tooltip">Save this question.<div className="s-popover--arrow"></div></div>
                    <a className="js-post-issue flex--item s-btn s-btn__unset c-pointer py6 mx-auto" href="/posts/74271381/timeline" data-shortcut="T" data-ks-title="timeline" data-controller="s-tooltip" data-s-tooltip-placement="right" aria-label="Timeline" aria-describedby="--stacks-s-tooltip-zzfrikcn"><TimerLogo /></a><div id="--stacks-s-tooltip-zzfrikcn" className="s-popover s-popover__tooltip" role="tooltip">Show activity on this post.<div className="s-popover--arrow"></div></div>
                </div>
            </div>
            <div className="s-prose js-post-body"> {/* 일단 디자인만 구현하기위해 예시글이고 나중에는 입력한 내용대로 들어오게 만들어야 함 */}
                <p>{body}</p>
            </div>
            <div className="d-flex gs8 s-anchors s-anchors__muted fw-wrap">
                <div className="flex--item"><a href="#">Delete</a></div>
                <div className="flex--item"onClick={handleEditQbtnClick}><a href="#">Edit</a></div>
            </div>
        </div>
    )

}

export default QuestionResponse;