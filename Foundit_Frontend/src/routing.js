import {createBrowserRouter} from "react-router-dom"
import Home from "./components/home";
import Login from "./components/login";
const route = createBrowserRouter([
    {
        path:"home",
        element:<Home />
    },
    {
        path:"login",
        element:<Login />
    }
])
export default route;