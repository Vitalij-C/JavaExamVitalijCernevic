import Navigation from "../part/Navigation.jsx";
import ContentBlock from "../part/ContentBlock.jsx";
import HeadingLarge from "../part/HeadingLarge.jsx";
import Footer from "../part/Footer.jsx";
import {useState} from "react";
import axios from "axios";
import {useAuth} from "../context/AuthContext.jsx";
import {useNavigate} from "react-router-dom";

const adCreatePage = () => {
    const {getUserData, getUserTokenHeader} = useAuth();
    const navigate = useNavigate();

    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("");
    const [price, setPrice] = useState("");
    const [city, setCity] = useState("");

    const inputTitle = (e) => {
        setTitle(e.target.value);
    }

    const inputDescription = (e) => {
        setDescription(e.target.value);
    }

    const inputPrice = (e) => {
        setPrice(e.target.value);
    }

    const inputCity = (e) => {
        setCity(e.target.value);
    }

    const inputSubmit = (e) => {
        e.preventDefault();

        const url = "http://localhost:8080/api/v1/ad"

        axios
            .post(url,
                {
                    title,
                    description,
                    price,
                    city,
                    author: getUserData().id
                },
                {
                    headers: getUserTokenHeader()
                }
            )
            .then((response) => {
                console.log(response);

                navigate("/profile");
            })
            .catch((error) => {
                console.error('Error: ', error);
            })
    }

    return <>
        <Navigation/>

        <ContentBlock>
            <HeadingLarge text="Add Create"/>

            <div>
                <form onSubmit={inputSubmit}>
                    <div>
                        Title
                    </div>
                    <div>
                        <input type="text" name="title" onChange={inputTitle}/>
                    </div>
                    <div>
                        Description
                    </div>
                    <div>
                        <input type="text" name="description" onChange={inputDescription}/>
                    </div>
                    <div>
                        Price
                    </div>
                    <div>
                        <input type="text" name="price" onChange={inputPrice}/>
                    </div>
                    <div>
                        City
                    </div>
                    <div>
                        <input type="text" name="city" onChange={inputCity}/>
                    </div>
                    <div>
                        <button type="submit">
                            Create
                        </button>
                    </div>
                </form>
            </div>
        </ContentBlock>

        <Footer/>
    </>
}

export default adCreatePage;