import React from "react";
import logo from "./logo.svg";
import "./App.css";
import DashBoard from "./components/DashBorad";
import Header from "./components/Layout/Header";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import AddProject from "./components/Project/AddProject";

function App() {
  return (
    <Router>
      <Switch>
        <div className="App">
          <Header />
          <Route path="/dashboard" component={DashBoard} exact />
          <Route path="/addProject" component={AddProject} exact />
        </div>
      </Switch>
    </Router>
  );
}

export default App;
