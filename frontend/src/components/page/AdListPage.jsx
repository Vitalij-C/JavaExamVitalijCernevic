import Navigation from "../part/Navigation.jsx";
import ContentBlock from "../part/ContentBlock.jsx";
import HeadingLarge from "../part/HeadingLarge.jsx";
import Footer from "../part/Footer.jsx";
import axios from "axios";
import React, {useEffect, useState} from "react";
import {useAuth} from "../context/AuthContext.jsx";
import {Link} from "react-router-dom";

const AdListPage = () => {
    const {getUserTokenHeader} = useAuth();
    const [ads, setAds] = useState([]);

    useEffect(() => {
        let url = "http://localhost:8080/api/v1/ad/all";

        axios
            .get(url,
                {
                    headers: getUserTokenHeader()
                }
            )
            .then((response) => {
                setAds(response.data);
            })
            .catch((error) => {
                console.error('Error: ', error);

                setAds([]);
            })
    }, []);

    return <>
        <Navigation/>

        <ContentBlock>
            <HeadingLarge text="Ad List"/>
        </ContentBlock>

        <div>
            {
                ads.map((ad, index) => {
                    const url = "/ad/view/" + ad.id;

                    return <div>
                        <div>Title: {ad.title}</div>
                        <div>Description: {ad.description}</div>
                        <Link to={url}>More</Link>
                    </div>
                })
            }
        </div>

        <Footer/>
    </>
}

export default AdListPage;