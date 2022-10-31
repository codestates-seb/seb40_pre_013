import styled from 'styled-components';
import { ReactComponent as GoogleLogo } from '../components/Icons/구글로고.svg';
import { ReactComponent as GithubLogo } from '../components/Icons/깃허브로고.svg';
import { ReactComponent as FaceLogo } from '../components/Icons/페북로고.svg';
import { ReactComponent as QuestionLogo } from '../components/Icons/questionMark.svg';
import { ReactComponent as PointLogo } from '../components/Icons/화살표.svg';
import { ReactComponent as BookMarkLogo } from '../components/Icons/bookmark.svg';
import { ReactComponent as TrophyLogo } from '../components/Icons/트로피.svg';

const SignUp = () => {
    return (
        <SignUpStyle>
            <div className='flex--item fs-body2 mr48 mb128 wmx4 md:d-none'>
                <h1 className='fs-headline1 mb32 lh-xs'>Join the Stack Overflow community</h1>
                <div className="d-flex mb24">
                    <div className="flex--item fc-blue-500 mr8">
                        <QuestionLogo/>
                    </div>
                    <div className="flex--item">Get unstuck — ask a question</div>
                </div>
                <div className="d-flex mb24">
                    <div className="flex--item fc-blue-500 mr8">
                        <PointLogo/>
                    </div>
                    <div className="flex--item">Unlock new privileges like voting and commenting</div>
                </div>
                <div className="d-flex mb24">
                    <div className="flex--item fc-blue-500 mr8">
                        <BookMarkLogo/>
                    </div>
                    <div className="flex--item">Save your favorite tags, filters, and jobs</div>
                </div>
                <div className="d-flex mb24">
                    <div className="flex--item fc-blue-500 mr8">
                        <TrophyLogo/>
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
                        <form class="d-flex gs4 gsy fd-column">
                            <label class="flex--item s-label" for="question-title">Display name</label>
                            <div class="d-flex ps-relative">
                                <input class="flex--item s-input" type="text" id="question-title" />
                            </div>
                            <label class="flex--item s-label" for="question-title">Email</label>
                            <div class="d-flex ps-relative">
                                <input class="flex--item s-input" type="text" id="question-title" />
                            </div>
                            <label class="flex--item s-label" for="question-title">Password</label>
                            <div class="d-flex ps-relative">
                                <input class="flex--item s-input" type="text" id="question-title" />
                            </div>
                            <p>
                            Passwords must contain at least eight characters, including at least 1 letter and 1 number.
                        </p>
                        </form>
                    </div>
                </div>
            </div>
            
        </SignUpStyle>



    )

}

const SignUpStyle = styled.div`
    `

export default SignUp