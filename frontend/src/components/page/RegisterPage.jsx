import Navigation from "../part/Navigation.jsx";
import ContentBlock from "../part/ContentBlock.jsx";
import HeadingLarge from "../part/HeadingLarge.jsx";
import Footer from "../part/Footer.jsx";

const RegisterPage = () => {
    return <>
        <Navigation/>

        <ContentBlock>
            <HeadingLarge text="Registration"/>
        </ContentBlock>

        <Footer/>
    </>
}

export default RegisterPage;