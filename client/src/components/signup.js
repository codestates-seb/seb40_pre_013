import styled from 'styled-components';
import { ReactComponent as GoogleLogo } from '../components/Icons/구글로고.svg';
import { ReactComponent as GithubLogo } from '../components/Icons/깃허브로고.svg';
import { ReactComponent as FaceLogo } from '../components/Icons/페북로고.svg';
import { ReactComponent as QuestionLogo } from '../components/Icons/questionMark.svg';
import { ReactComponent as PointLogo } from '../components/Icons/화살표.svg';
import { ReactComponent as BookMarkLogo } from '../components/Icons/bookmark.svg';
import { ReactComponent as TrophyLogo } from '../components/Icons/트로피.svg';
import { Link } from 'react-router-dom';

const SignUp = () => {
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
                            <form class="d-flex gs4 gsy fd-column">
                                <div className='signupInput'>
                                    <label class="flex--item s-label" for="question-title">Display name</label>
                                    <div class="d-flex ps-relative">
                                        <input class="flex--item s-input" type="text" id="question-title" />
                                    </div>
                                </div>
                                <div className='signupInput'>
                                    <label class="flex--item s-label" for="question-title">Email</label>
                                    <div class="d-flex ps-relative">
                                        <input class="flex--item s-input" type="text" id="question-title" />
                                    </div>
                                </div>
                                <div className='signupInput'>
                                    <label class="flex--item s-label" for="question-title">Password</label>
                                    <div class="d-flex ps-relative">
                                        <input class="flex--item s-input" type="text" id="question-title" />
                                    </div>
                                    <p className='pwCondition'>
                                        Passwords must contain at least eight characters, including at least 1 letter and 1 number.
                                    </p>
                                </div>
                                <fieldset>
                                    <div class="d-flex gs8">
                                        <div class="flex--item">
                                            <input class="s-checkbox" type="checkbox" name="example-name" id="example-item" />
                                        </div>
                                        <label class="flex--item s-label fw-normal" for="example-item">Opt-in to receive occasional product updates, user research invitations, company announcements, and digests.</label>
                                    </div>
                                </fieldset>
                                <a href="#" className="s-topbar--item s-topbar--item__unset ml4 s-btn s-btn__primary">Sign up</a>
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
    `

export default SignUp