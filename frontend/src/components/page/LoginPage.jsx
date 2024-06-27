import Navigation from "../part/Navigation.jsx";
import ContentBlock from "../part/ContentBlock.jsx";
import HeadingLarge from "../part/HeadingLarge.jsx";
import Footer from "../part/Footer.jsx";
import LoginForm from "../part/LoginForm.jsx";

const LoginPage = () => {
    return <>
        <Navigation/>

        <ContentBlock>
            <HeadingLarge text="Login"/>

            <LoginForm/>
        </ContentBlock>

        <Footer/>
    </>
}

export default LoginPage;