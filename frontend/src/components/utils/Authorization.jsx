import {useAuth} from '../context/AuthContext';
import {Navigate, Outlet} from 'react-router-dom';


export const Authorization = ({state}) => {
    const {isUserLoggedIn} = useAuth();

    if (state !== isUserLoggedIn()) {
        return <Navigate to={'/error'}/>;
    }

    return <Outlet/>;
};

export const AuthorizationWithRole = ({roles}) => {
    const {isLoggedIn, getRole} = useAuth();

    if (!isLoggedIn()) {
        return <Navigate to={'/login'}/>;
    }

    if (!roles.includes(getRole())) {
        return <Navigate to={'/error'}/>;
    }

    return <Outlet/>;
};

export const IsAuthorized = () => {
    const {isUserLoggedIn} = useAuth();

    if (!isUserLoggedIn()) {
        return <Navigate to={'/error'}/>;
    }

    return <Outlet/>;
};

export const IsNotAuthorized = () => {
    const {isUserLoggedIn} = useAuth();

    if (isUserLoggedIn()) {
        return <Navigate to={'/error'}/>;
    }

    return <Outlet/>;
};