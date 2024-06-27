import {Route, Routes} from "react-router-dom";
import LoginPage from "./components/page/LoginPage.jsx";
import RegisterPage from "./components/page/RegisterPage.jsx";
import ProfilePage from "./components/page/ProfilePage.jsx";
import ErrorPage from "./components/page/ErrorPage.jsx";

import {AuthProvider} from "./components/context/AuthContext.jsx";
import {Authorization} from "./components/utils/Authorization.jsx";
import AdCreatePage from "./components/page/AdCreatePage.jsx";
import AdViewPage from "./components/page/AdViewPage.jsx";
import AdListPage from "./components/page/AdListPage.jsx";

function App() {
    return (
        <AuthProvider>
            <Routes>
                <Route element={<Authorization state={false}/>}>
                    <Route path="/" element={<LoginPage/>}/>
                    <Route path="/login" element={<LoginPage/>}/>
                    <Route path="/register" element={<RegisterPage/>}/>
                </Route>

                <Route element={<Authorization state={true}/>}>
                    <Route path="/profile" element={<ProfilePage/>}/>
                    <Route path="/ad/create" element={<AdCreatePage/>}/>
                    <Route path="/ad/view" element={<AdListPage/>}/>
                    <Route path="/ad/view/:id" element={<AdViewPage/>}/>
                </Route>

                <Route path="*" element={<ErrorPage/>}/>
            </Routes>
        </AuthProvider>
    )
}

export default App