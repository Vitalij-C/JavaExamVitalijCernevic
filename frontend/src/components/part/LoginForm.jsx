import {useState} from "react";
import axios from "axios";
import {useAuth} from "../context/AuthContext.jsx";
import {useNavigate} from "react-router-dom";

const LoginForm = () => {
    const navigate = useNavigate();

    const {setAuth} = useAuth();

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState(false);

    const inputEmail = (e) => {
        setEmail(e.target.value);
        setError(false);
    }

    const inputPassword = (e) => {
        setPassword(e.target.value);
        setError(false);
    }

    const inputSubmit = (e) => {
        e.preventDefault();

        const url = "http://localhost:8080/api/v1/auth/login"

        axios
            .post(url, {
                email,
                password
            })
            .then((response) => {
                console.log(response);

                setAuth(response.data);

                navigate("/profile");
            })
            .catch((error) => {
                console.error('Error: ', error);

                setError(true);
            })

        return e;
    }

    return <>
        <form onSubmit={inputSubmit}>
            <div>
                Email:
            </div>
            <div>
                <input type="text" name="email" onChange={inputEmail}/>
            </div>
            <div>
                Password:
            </div>
            <div>
                <input type="password" name="password" onChange={inputPassword}/>
            </div>
            <div>
                <button type="submit">
                    Submit
                </button>
            </div>
        </form>

        {error && <div>Error</div>}
    </>
}

export default LoginForm;