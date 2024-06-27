import {useAuth} from '../context/AuthContext';

export const Authentication = ({children, state}) => {
    const {isUserLoggedIn} = useAuth();

    if (isUserLoggedIn() !== state) {
        return;
    }

    return children;
};

export const AuthenticationWithRole = ({children, roles}) => {
    const {isUserLoggedIn, getRole} = useAuth();

    if (!isUserLoggedIn()) {
        return;
    }

    if (!roles.includes(getRole())) {
        return;
    }

    return children;
};