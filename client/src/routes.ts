import { MainForm, LeaderDashboard, SignIn, SignUp, NotFound } from "./pages"
import { ADMIN__ROUTE, LOGIN__ROUTE, NOT_FOUND, REGISTRATION__ROUTE,ADMIN__PAGE } from "./utils/consts"




export const authRoutes = [
    {
        path: ADMIN__ROUTE,
        Component: MainForm 
    },
    {
        path: ADMIN__PAGE,
        Component: LeaderDashboard
    }
    
]
export const publicRoutes = [
    {
        path: '/',
        Component: SignIn
    },
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