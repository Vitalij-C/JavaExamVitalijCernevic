import Navigation from "../part/Navigation.jsx";
import ContentBlock from "../part/ContentBlock.jsx";
import HeadingLarge from "../part/HeadingLarge.jsx";
import Footer from "../part/Footer.jsx";
import {useAuth} from "../context/AuthContext.jsx";
import {Link} from "react-router-dom";

const ProfilePage = () => {
    const {getUserData} = useAuth();

    return <>
        <Navigation/>

        <ContentBlock>
            <HeadingLarge text="Profile"/>

            <div>
                <div>Email: {getUserData().email}</div>
                <div>Role: {getUserData().role}</div>
            </div>

            <p>Actions</p>
            <ul>
                <li>
                    <Link to={"/ad/create"}>{"Create ad"}</Link>
                </li>
                <li>
                    <Link to={"/ad/view"}>{"View ads"}</Link>
                </li>
            </ul>
        </ContentBlock>

        <Footer/>
    </>
}

export default ProfilePage;
