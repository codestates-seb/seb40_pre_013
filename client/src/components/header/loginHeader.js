import { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import styled from 'styled-components'
import { ReactComponent as StackLogo } from '../Icons/logoStackoverflow.svg'
import { ReactComponent as Magnifier } from '../Icons/돋보기.svg'

 
const LoginHeader = (props) => {
    const [wirte, setWrite] = useState('') // 검색창 글쓰기 구현 

    const changeMsg = (event) => {
        setWrite(event.target.value);
    }

    const isLogin = props.isLogin
    const navigate = useNavigate();

    const onLogout = () => {
    	// sessionStorage 에 username으로 저장되어있는 아이템을 삭제한다.
        sessionStorage.removeItem('username')
        // 로그인페이지로 이동(새로고침)
        window.location.href = '/login'
    }

    return (
        <HeaderStyle>
            <header className="s-topbar">
                <div className='s-topbar--container'>
                <a href="/" className="s-topbar--logo"><StackLogo /></a>
                <form id="search" className="s-topbar--searchbar" autoComplete="off">
                    <div className="s-topbar--searchbar--input-group">
                        <input
                            onChange={changeMsg}
                            type="text"
                            placeholder="Search…"
                            value={wirte}
                            autoComplete="off"
                            className="s-input s-input__search" />
                        <Magnifier />
                    </div>
                </form>
                <ol className="s-topbar--content">
                    <li>
                        <button type='button' onClick={onLogout} className="s-topbar--item s-topbar--item__unset ml4 s-btn s-btn__primary">Log out</button>
                    </li>
                </ol>
                </div>
            </header>
        </HeaderStyle>
     
    )
}

// 헤더 간격 최대한 비슷하게 구현 반응형은 구현하지 않음 


const HeaderStyle = styled.header`

.s-topbar--logo {
    margin-left: 0;
    width: 150px;
    height: 30px;
    margin-top: -4px;
}

`

export default LoginHeader