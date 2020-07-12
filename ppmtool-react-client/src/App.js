import React from "react";
import logo from "./logo.svg";
import "./App.css";
import DashBoard from "./components/DashBorad";
import Header from "./components/Layout/Header";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router, Route } from "react-router-dom";
import AddProject from "./components/Project/AddProject";
import { Provider } from "react-redux";
import store from "./store";

function App() {
  return (
    <Provider store={store}>
      <Router>
        <div className="App">
          <Header />
          <Route path="/dashboard" component={DashBoard} exact />
          <Route path="/addProject" component={AddProject} exact />
        </div>
      </Router>
    </Provider>
  );
}

export default App;
