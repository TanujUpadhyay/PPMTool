import React from "react";
import logo from "./logo.svg";
import "./App.css";
import DashBoard from "./components/DashBorad";
import Header from "./components/Layout/Header";
import "bootstrap/dist/css/bootstrap.min.css";

function App() {
  return (
    <div className="App">
      <Header />
      <DashBoard />
    </div>
  );
}

export default App;
