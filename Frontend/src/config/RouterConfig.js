import React from "react";
import routes from "./routes.json";
import { Route, Routes } from "react-router-dom";
import Home from "../pages/Home";
import Login from "../pages/Login";
import Register from "../pages/Register";
import Menu1 from "../pages/Menu1";
import Menu2 from "../pages/Menu2";

const componentMapping = {
    Home,
    Login,
    Register,
    Menu1,
    Menu2
};

const renderRouter = (routes) => {
    return routes.map((route, index) => {
        const Component = componentMapping[route.component];
        return (
            <Route key={index} path={route.path} element = <Component/>  >
                {route.routes && renderRouter(route.routes)}
            </Route>
        );
    });
};

const RouterConfig = () => <Routes>{renderRouter(routes)}</Routes>;

export default RouterConfig;
