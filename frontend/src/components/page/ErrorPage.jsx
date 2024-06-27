import Navigation from "../part/Navigation.jsx";
import ContentBlock from "../part/ContentBlock.jsx";
import HeadingLarge from "../part/HeadingLarge.jsx";
import Footer from "../part/Footer.jsx";

const ErrorPage = () => {
    return <>
        <Navigation/>

        <ContentBlock>
            <HeadingLarge text="Error"/>
        </ContentBlock>

        <Footer/>
    </>
}

export default ErrorPage;