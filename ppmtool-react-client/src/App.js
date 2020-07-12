import React from "react";
import "./App.css";
import DashBoard from "./components/DashBorad";
import Header from "./components/Layout/Header";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router, Route } from "react-router-dom";
import AddProject from "./components/Project/AddProject";
import { Provider } from "react-redux";
import store from "./store";
import UpdateProject from "./components/Project/UpdateProject";

function App() {
  return (
    <Provider store={store}>
      <Router>
        <div className="App">
          <Header />

          <Route path="/dashboard" component={DashBoard} exact />

          <Route path="/addProject" component={AddProject} exact />

          <Route path="/updateProject/:id" component={UpdateProject} exact />
        </div>
      </Router>
    </Provider>
  );
}

export default App;
