import React from "react";
import styled from "styled-components";
import { ImEarth } from 'react-icons/im';


function SideBar() {
    return (
      <Side>
        <Navigation>
            <Ol>
                <li className="liHome">
                  Home
                </li>
                <li className="liPublic">Public</li>
                <li className="liList">
                     <ImEarth/> Questions
                </li>
            </Ol>
         </Navigation>
      </Side>   
    )
}

export default SideBar

const Side = styled.div`
width: 164px;
`

const Navigation = styled.div`
  position: sticky;
  top: 56px;
  display: flex;
  align-items: center;
  width: 164px;
  background: #ffffff;
  margin: 0;
  padding-top: 24px;
  padding-bottom: 4rem;
`
const Ol = styled.ol`
  display: flex;
  align-items: center;
  justify-content: left;
  flex-direction: column;
  height: 100%;
  width: 100%;
  list-style: none;
  margin: 0;
  padding: 0;
  font-size: 13px;
  color: #0C0D0E;
  cursor: pointer;

  .liHome {
    display: flex;
    justify-content: space-between;
    width: 100%;
    font-size: 13px;
    color: #525960;
    padding: 4px 4px 4px 8px;
    svg{
      width: 40px;
    }
  }
  .liPublic {
    display: flex;
    justify-content: space-between;
    width: 100%;
    font-size: 13px;
    margin: 16px 0 4px 8px;
    svg{
      width: 40px;
    }
  }
  .liList {
    display: flex;
    justify-content: left;
    align-items: center;
    padding-left: 16px;
    width: 164px;
    height: 33px;
    color: #0C0D0E;

    background-color: hsl(210,8%,95%);
    border-right: 3px solid hsl(27,90%,55%);
    font-weight: bold;
  }
`