import styled from 'styled-components';


const Footer = () => {
  return (
    <footer>
      <FooterStyle>
        <div>
          <svg aria-hidden="true" width="32" height="37" viewBox="0 0 32 37">
            <path d="M26 33v-9h4v13H0V24h4v9h22Z" fill="#BCBBBB"></path>
            <path
              d="m21.5 0-2.7 2 9.9 13.3 2.7-2L21.5 0ZM26 18.4 13.3 7.8l2.1-2.5 12.7 10.6-2.1 2.5ZM9.1 15.2l15 7 1.4-3-15-7-1.4 3Zm14 10.79.68-2.95-16.1-3.35L7 23l16.1 2.99ZM23 30H7v-3h16v3Z"
              fill="#F48024"
            ></path>
          </svg>
        </div>
        <div className="footer-column">
          <div className="col">
            <ul>
              <h5>STACK OVERFLOW</h5>
              <li>Ouestions</li>
              <li>Help</li>
            </ul>
          </div>
          <div>
            <ul>
              <h5>PRODUCTS</h5>
              <li>Teams</li>
              <li>Advertising</li>
              <li>Collectives</li>
              <li>Talent</li>
            </ul>
          </div>
          <div>
            <ul>
              <h5>COMPANY</h5>
              <li>About</li>
              <li>Press</li>
              <li>Work Here</li>
              <li>Privacy Policy</li>
            </ul>
          </div>
          <div>
            <ul>
              <h5> STACK EXCHANGE NETWORK</h5>
              <li>Technology Culture</li>
              <li>Culture & recreation</li>
              <li>Life & arts</li>
              <li>Science</li>
              <li>Professional</li>
              <li>Business</li>
              <li>API</li>
              <li>Data</li>
            </ul>
          </div>
        </div>
        <div className='footer-right'>
            <div className="footer-side">
                Blog  Facebook  Twitter  LinkedIn  Instagram
            </div>
            <div className="footer-side">
                Site design / logo © 2022 Stack Exchange Inc; user <br/>
                contributions licensed under CC BY-SA. <br/>
                rev 2022.10.26.42986
            </div>
        </div>
      </FooterStyle>
    </footer>
  );
};

const FooterStyle = styled.div`
  padding: 32px 35px 12px 35px;
  display: flex;
  flex-direction: row;
  background: rgba(36, 38, 41);
  color: rgba(187, 191, 196);
  overflow: hidden;
  width: 100%;
  height: 322px;
  font-size: 0.9rem;
  .footer-column {
    display: flex;
    flex: 2 1 auto;
    flex-wrap: wrap;
    line-height: 1.5rem;
  }
  h5 {
    font-size: 1rem;
    margin-bottom: 10px;
  }
  div {
    flex: 1 0 auto;
  }
  ul {
    display: block;
  }
  .footer-copy-side {
    flex: 1 1 150px;
    display: flex;
    flex-direction: column;
    font-size: 0.8rem;
    margin-top: auto;
    margin-bottom: 30px;
  }
  .footer-right {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
  }
`;



export default Footer;