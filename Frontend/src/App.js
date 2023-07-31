import "./App.css";
import React from "react";
import { BrowserRouter as Router, Routes } from "react-router-dom";
import RouterConfig from "./config/RouterConfig";

export default function App() {
    return (
        <Router>
            <RouterConfig />
        </Router>
    );
}
