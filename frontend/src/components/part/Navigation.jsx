import {Link, NavLink} from "react-router-dom";
import {useState} from "react";
import {Authentication} from "../utils/Authentication.jsx";
import {useAuth} from "../context/AuthContext.jsx";

const Navigation = () => {
    const [navState, setNavState] = useState(false)
    const {unsetAuth} = useAuth();

    const navStateClass = navState ? "nav-open" : "";

    const navToggle = () => {
        setNavState(!navState);
    }

    return <>
        <header id="navigation" className={navStateClass}>
            <div>
                <div className="navigation__logo-section">
                    <Link to="/">
                        <img src="/public/logo.svg" alt="logo"/>
                    </Link>
                </div>

                <nav className="navigation__button-section">
                    <ul>

                        <Authentication state={false}>
                            <li>
                                <NavLink to={"/login"}>{"Login"}</NavLink>
                            </li>
                            <li>
                                <NavLink to={"/register"}>{"Register"}</NavLink>
                            </li>
                        </Authentication>

                        <Authentication state={true}>
                            <li>
                                <NavLink to={"/profile"}>{"Profile"}</NavLink>
                            </li>
                            <li>
                                <Link to={"/login"} onClick={unsetAuth}>{"Logout"}</Link>
                            </li>
                        </Authentication>
                    </ul>
                </nav>

                <button className="navigation__nav-toggle" onClick={navToggle}></button>
            </div>
        </header>
    </>
}

export default Navigation;