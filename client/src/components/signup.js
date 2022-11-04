import styled from 'styled-components';
import { ReactComponent as GoogleLogo } from '../components/Icons/구글로고.svg';
import { ReactComponent as GithubLogo } from '../components/Icons/깃허브로고.svg';
import { ReactComponent as FaceLogo } from '../components/Icons/페북로고.svg';
import { ReactComponent as QuestionLogo } from '../components/Icons/questionMark.svg';
import { ReactComponent as PointLogo } from '../components/Icons/화살표.svg';
import { ReactComponent as BookMarkLogo } from '../components/Icons/bookmark.svg';
import { ReactComponent as TrophyLogo } from '../components/Icons/트로피.svg';
import { Link, useNavigate } from 'react-router-dom';
import { useState } from 'react';
import axios from 'axios';

const SignUp = () => {
    // 초기값 - 이메일 , 닉네임, 비밀번호
    const [inputId, setInputId] = useState('');
    const [inputPw, setInputPw] = useState('');
    const [inputName, setInputName] = useState('');

    // 오류메세지 상태 
    const [idMessage, setIdMessage] = useState("");
    const [nameMessage, setNameMessage] = useState("");
    const [passwordMessage, setPasswordMessage] = useState("");

    // 유효성 검사 
    const [isId, setIsId] =  useState(false);
    const [isname, setIsName] =  useState(false);
    const [isPassword, setIsPassword] =  useState(false);

    // 정규 표현식을 이용한 유효성 검사 (ID)
    const onChangeId = (e) => {
        const currentId = e.target.value;
        setInputId(currentId);
        const idRegExp =
          /^[A-Za-z0-9_]+[A-Za-z0-9]*[@]{1}[A-Za-z0-9]+[A-Za-z0-9]*[.]{1}[A-Za-z]{1,3}$/;
     
        if (!idRegExp.test(currentId)) {
          setIdMessage("이메일의 형식이 올바르지 않습니다!");
          setIsId(false);
        } else {
          setIdMessage("사용 가능한 이메일 입니다.");
          setIsId(true);
        }
      };

    // 정규 표현식을 이용한 유효성 검사 (닉네임)
    const onChangeName = (e) => {
        const currentName = e.target.value;
        setInputName(currentName);

        if (currentName.length < 2 || currentName.length > 5) {
            setNameMessage("닉네임은 2글자 이상 5글자 이하로 입력해주세요!");
            setIsName(false);
        } else {
            setNameMessage("사용가능한 닉네임 입니다.");
            setIsName(true);
        }
    };

    // 정규 표현식을 이용한 유효성 검사 (비밀번호)
    const onChangePassword = (e) => {
        const currentPassword = e.target.value;
        setInputPw(currentPassword);
        const passwordRegExp =
            /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/;
        if (!passwordRegExp.test(currentPassword)) {
            setPasswordMessage(
                "숫자+영문자+특수문자 조합으로 8자리 이상 입력해주세요!"
            );
            setIsPassword(false);
        } else {
            setPasswordMessage("안전한 비밀번호 입니다.");
            setIsPassword(true);
        }
    };

    const navigate = useNavigate();

    const onClickLogin = (e) => {
        e.preventDefault(); // 새로고침 방지 
        axios.post('https://4a57-36-38-67-6.jp.ngrok.io/members', {
            email: inputId,
            displayName : inputName,
            password: inputPw
        })
        .then(() => {
            navigate('/login'); // 연결이 됬다면 로그인 페이지로 이동 
        })
        .catch()
    }

    return (
        <SignUpStyle>
            <div className='d-flex flex__center snippet-hidden'>
                <div className='flex--item fs-body2 mr48 mb128 wmx4 md:d-none'>
                    <h1 className='fs-headline1 mb32 lh-xs'>Join the Stack Overflow community</h1>
                    <div className="d-flex mb24">
                        <div className="flex--item fc-blue-500 mr8">
                            <QuestionLogo />
                        </div>
                        <div className="flex--item">Get unstuck — ask a question</div>
                    </div>
                    <div className="d-flex mb24">
                        <div className="flex--item fc-blue-500 mr8">
                            <PointLogo />
                        </div>
                        <div className="flex--item">Unlock new privileges like voting and commenting</div>
                    </div>
                    <div className="d-flex mb24">
                        <div className="flex--item fc-blue-500 mr8">
                            <BookMarkLogo />
                        </div>
                        <div className="flex--item">Save your favorite tags, filters, and jobs</div>
                    </div>
                    <div className="d-flex mb24">
                        <div className="flex--item fc-blue-500 mr8">
                            <TrophyLogo />
                        </div>
                        <div className="flex--item">Earn reputation and badges</div>
                    </div>
                    <div className='fs-body1 fc-light'>
                        Collaborate and share knowledge with a private group for FREE.
                        <a href='#'>Get Stack Overflow for Teams free for up to 50 users</a>
                    </div>
                </div>
                <div>
                    <div className='flex--item fl-shrink0'>
                        <div className='mx-auto d-flex flex__fl-grow1 fd-column gs8 gsy mb16 wmx3'>
                            <button className="flex--item s-btn s-btn__icon s-btn__google bar-md ba bc-black-100">
                                <GoogleLogo />Log in with Google</button>
                            <button className='flex--item s-btn s-btn__icon s-btn__github bar-md ba bc-black-100'><GithubLogo />Log in with GitHub</button>
                            <button className='flex--item s-btn s-btn__icon s-btn__facebook bar-md'><FaceLogo />Log in with Facebook</button>
                        </div>
                        <div className='mx-auto mb24 p24 wmx3 bg-white bar-lg bs-xl mb24 with-captcha'>
                            <form className="d-flex gs4 gsy fd-column">
                                <div className='signupInput'>
                                    <label className="flex--item s-label" htmlFor="question-title">Display name</label>
                                    <div className="d-flex ps-relative">
                                        <input className="flex--item s-input" type="text" id="question-title" value={inputName} onChange={onChangeName} />
                                    </div>
                                    <div>{nameMessage}</div>
                                </div>
                                <div className='signupInput'>
                                    <label className="flex--item s-label" htmlFor="question-title">Email</label>
                                    <div className="d-flex ps-relative">
                                        <input className="flex--item s-input" type="text" id="question-title" value={inputId} onChange={onChangeId} />
                                    </div>
                                    <div>{idMessage}</div>
                                </div>
                                <div className='signupInput'>
                                    <label className="flex--item s-label" htmlFor="question-title">Password</label>
                                    <div className="d-flex ps-relative">
                                        <input className="flex--item s-input" type="password" id="question-title" value={inputPw} onChange={onChangePassword} />
                                    </div>
                                    <div>{passwordMessage}</div>
                                    <p className='pwCondition'>
                                        Passwords must contain at least eight characters, including at least 1 letter and 1 number.
                                    </p>
                                </div>
                                <div className='imNotRobot'>
                                    <input type='checkbox' className='robotcheck'></input>
                                    <label></label>
                                    <span className='robotName'>I'm not a robot</span>
                                </div>
                                <fieldset>
                                    <div className="d-flex gs8">
                                        <div className="flex--item">
                                            <input className="s-checkbox" type="checkbox" name="example-name" id="example-item" />
                                        </div>
                                        <label className="flex--item s-label fw-normal" htmlFor="example-item">Opt-in to receive occasional product updates, user research invitations, company announcements, and digests.</label>
                                    </div>
                                </fieldset>
                                <button className="s-topbar--item s-topbar--item__unset ml4 s-btn s-btn__primary" onClick={onClickLogin}>Sign up</button>
                            </form>
                            <div className='js-terms fs-caption fc-light ta-left mt32'>
                                By clicking “Sign up”, you agree to our <a href='#'>terms of service, privacy policy</a> and <a href='#'>cookie policy</a>
                            </div>
                        </div>
                        <div className='mx-auto ta-center fs-body1 p16 pb0 mb24 w100 wmx3 js-redirects'>
                            Already have an account?<Link to='/login'>Log in</Link>
                            <div className='ruEmployer'>
                                Are you an employer?<a href='#'>Sign up on Talent </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


        </SignUpStyle>



    )

}

const SignUpStyle = styled.div`
      background-color: #f1f2f3;
      width: 100%;
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      gap: 24px 0;
      
    .signupInput {
        margin-top : 6px;
        margin-bottom : 6px;
    }
    .pwCondition {
        margin-top : 4px;
        margin-bottom : 4px;
        color : gray;
    }
    .gs8 {
        margin-top : 6px;
        margin-bottom : 6px;
    }
    .s-btn__primary {
        margin-top : 6px;
        margin-bottom : 6px;
    }
    .ruEmployer {
        margin-top : 12px;
    }
    .d-flex {
        padding : 12px;
    }
    .imNotRobot {
        display:flex;
        justify-content:center;
        align-items:center;
        border: 1px solid gray;
        width:156px;
        height:136px;
        margin: auto;
    }
    .robotcheck {
        display: inline-block;
        width: 24px;
        height: 24px;
        margin-right: 10px;
    }
    .robotName {
        font-size : 15px;
    }
    `

export default SignUp