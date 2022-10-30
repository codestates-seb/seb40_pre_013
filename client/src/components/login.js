import React from 'react';
import styled from 'styled-components';
import { ReactComponent as LoginStackLogo } from '../components/Icons/로그인스택오버플로우로고.svg';
import { ReactComponent as GoogleLogo } from '../components/Icons/구글로고.svg';
import { ReactComponent as GithubLogo } from '../components/Icons/깃허브로고.svg';
import { ReactComponent as FaceLogo } from '../components/Icons/페북로고.svg';
import { Link } from 'react-router-dom';

const Login = () => {
    // 배경색을 어떻게 바꿔야 할지 몰라서 구현을 아직 못함 
    return (
        <LoginStyle>
            <div>
                <div className='logos'>
                    <div className='loginStack'><LoginStackLogo /></div>
                    <button className="flex--item s-btn s-btn__icon s-btn__google bar-md ba bc-black-100">
                        <GoogleLogo />Log in with Google</button>
                    <button className='flex--item s-btn s-btn__icon s-btn__github bar-md ba bc-black-100'><GithubLogo />Log in with GitHub</button>
                    <button className='flex--item s-btn s-btn__icon s-btn__facebook bar-md'><FaceLogo />Log in with Facebook</button>
                    <div class="s-card bs-md">
                        <form class="d-flex gs4 gsy fd-column">
                            <div className='emailInput'>
                                <label class="flex--item s-label" for="question-title">Email</label>
                                <div class="d-flex ps-relative">
                                    <input class="flex--item s-input" type="text" id="question-title" />
                                </div>
                            </div>
                            <div className='passwordInput'>
                                <label class="flex--item s-label" for="question-title">Password</label>
                                <div class="d-flex ps-relative">
                                    <input class="flex--item s-input" type="text" id="question-title" />
                                </div>
                            </div>

                            <a href="…" className="s-topbar--item s-topbar--item__unset ml4 s-btn s-btn__primary">Log in</a>
                        </form>
                    </div>
                    <div className='loginQustion'>
                        Don’t have an account? <Link to='/signup'>Sign up</Link>
                        <div className='employ'>
                            Are you an employer?<a href='#'> Sign up on Talent</a>
                        </div>
                    </div>
                </div>
            </div>
        </LoginStyle>


    )
}

const LoginStyle = styled.div`
   .logos {
    display:flex;
    flex-direction:column;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 330px;
   }
   .s-btn__google {
    margin:4px;
   }
   .s-btn__github {
    margin:4px;
   }
   .s-btn__facebook {
    margin:4px;
    margin-bottom:16px;
   }
   .loginBox {
    border: 1px solid black;
    height: 180px;
   }
   .s-card{
    padding: 24px;
    margin-bottom:24px;
   }
   .emailInput {
    margin-top: 6px;
    margin-bottom: 6px;
   }
   .passwordInput {
    margin-top: 6px;
    margin-bottom: 6px;
   }
   .loginStack {
    text-align: center;
    margin-bottom: 24px;
   }
   .s-topbar--item__unset {
    margin-top: 6px;
    margin-bottom: 6px;
   }
   .loginQustion {
    font-size:13px;
    text-align: center;
    padding:16px;
    margin-bottom: 24px;
   }
   .employ {
    margin-top:12px;
   }
`

export default Login
