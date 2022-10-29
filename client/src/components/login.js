import React from 'react';
import styled from 'styled-components';
import Header from './header/header';

const Login = () => {

    return (
        <LoginStyle>
            <div>
                <ul className='login'>
                    <li><a href='#'>Log in with Google</a></li>
                    <li><a href='#'>Log in with GitHub</a></li>
                    <li><a href='#'>Log in with Facebook</a></li>
                </ul>
            </div>
        </LoginStyle>


    )
}

const LoginStyle = styled.div`
    .login {
        list-style : none;
        position: absolute;
        left: 50%;
        top: 50%;
        transform : translate(-50%,-50%);
    }
`

export default Login
