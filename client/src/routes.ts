import NotFound from "./pages/NotFound"
import SignIn from "./pages/SignIn"
import SignUp from "./pages/SignUp"
import MainForm from "./pages/MainForm"
import { ADMIN__ROUTE, LOGIN__ROUTE, NOT_FOUND, REGISTRATION__ROUTE } from "./utils/consts"



export const authRoutes = [
    {
        path: ADMIN__ROUTE,
        Component: MainForm 
    },
    
]
export const publicRoutes = [
    {
        path:LOGIN__ROUTE,
        Component: SignIn
    },
    {
        path:REGISTRATION__ROUTE,
        Component:SignUp
    },
    {
        path: NOT_FOUND,
        Component: NotFound
    },
    {
        path: ADMIN__ROUTE,
        Component: MainForm 
    }
]