import Navigation from "../part/Navigation.jsx";
import ContentBlock from "../part/ContentBlock.jsx";
import HeadingLarge from "../part/HeadingLarge.jsx";
import Footer from "../part/Footer.jsx";
import axios from "axios";
import React, {useEffect, useState} from "react";
import {useAuth} from "../context/AuthContext.jsx";
import {Link, useParams} from "react-router-dom";

const AdViewPage = () => {
    const {getUserTokenHeader} = useAuth();
    const [ad, setAd] = useState(false);

    const params = useParams();
    const uuid = params.id;

    useEffect(() => {
        const url = "http://localhost:8080/api/v1/ad/" + uuid;

        axios
            .get(url,
                {
                    headers: getUserTokenHeader()
                }
            )
            .then((response) => {
                setAd(response.data);
            })
            .catch((error) => {
                console.error('Error: ', error);

                setAd(false);
            })
    }, []);

    return <>
        <Navigation/>

        <ContentBlock>
            <HeadingLarge text="Ad View"/>

            {ad &&
                <div>
                    <div>Title: {ad.title}</div>
                    <div>Description: {ad.description}</div>
                </div>
            }

        </ContentBlock>

        <Footer/>
    </>
}

export default AdViewPage;