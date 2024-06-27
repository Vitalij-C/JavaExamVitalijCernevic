import {createContext, useContext, useState} from "react";

const AuthContext = createContext();


const setStorageToken = (token) => {
    if (!token) {
        return;
    }

    localStorage.setItem('user_token', token);
};

const getStorageToken = () => {
    return localStorage.getItem('user_token');
};

const unsetStorageToken = () => {
    localStorage.removeItem('user_token');
};

const setStorageUserData = (userData) => {
    if (!userData) {
        return;
    }

    localStorage.setItem('user_data', JSON.stringify(userData));
};

const getStorageUserData = () => {
    const u = localStorage.getItem('user_data');

    if (!u) {
        return null;
    }

    return JSON.parse(u);
};

const unsetStorageUserData = () => {
    localStorage.removeItem('user_data');
};

export const AuthProvider = ({children}) => {
    const [token, setToken] = useState(getStorageToken() || null);
    const [userData, setUserData] = useState(getStorageUserData() || null);

    const setAuth = (auth) => {
        setToken(auth.token);
        setUserData(auth.user);

        setStorageToken(auth.token);
        setStorageUserData(auth.user);
    }

    const unsetAuth = () => {
        setToken(null);
        setUserData(null);

        unsetStorageToken();
        unsetStorageUserData()
    }

    const getUserToken = () => {
        return token;
    };

    const getUserData = () => {
        return userData;
    };

    const getUserRole = () => {
        if (!isUserLoggedIn()) {
            return null;
        }

        return userData.role;
    };

    const isUserLoggedIn = () => {
        return !!token && !!userData;
    };

    const getUserTokenHeader = () => {
        if (!isUserLoggedIn()) {
            return null;
        }

        return {
            Authorization: `Bearer ${token}`
        };
    };

    return <AuthContext.Provider value={{
        setAuth,
        unsetAuth,
        getUserToken,
        getUserData,
        getUserRole,
        isUserLoggedIn,
        getUserTokenHeader
    }}>
        {children}
    </AuthContext.Provider>;
}

export const useAuth = () => useContext(AuthContext);