import React from "react";
import "./App.css";
import DashBoard from "./components/DashBorad";
import Header from "./components/Layout/Header";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import AddProject from "./components/Project/AddProject";
import { Provider } from "react-redux";
import store from "./store";
import UpdateProject from "./components/Project/UpdateProject";
import Landing from "./components/Layout/Landing";
import Register from "./components/UserManagment/Register";
import Login from "./components/UserManagment/Login";
import jwt_decode from "jwt-decode";
import setJWTToken from "./secutityUtils/setJWTToken";
import { SET_CURRENT_USER } from "./actions/types";
import AddProjectTask from "./components/ProjectBoard/ProjectTask/AddProjectTask";
import ProjectBoard from "./components/ProjectBoard/ProjectBoard";
import UpdateProjectTask from "./components/ProjectBoard/ProjectTask/UpdateProjectTask";
import { logout } from "./actions/securityActions";
import SecureRoutes from "./secutityUtils/SecureRoutes";

const jwtToken = localStorage.jwtToken;

if (jwtToken) {
  setJWTToken(jwtToken);
  const decoded_jwtToken = jwt_decode(jwtToken);
  store.dispatch({
    type: SET_CURRENT_USER,
    payload: decoded_jwtToken,
  });

  const currentTime = Date.now() / 1000;
  if (decoded_jwtToken.exp < currentTime) {
    store.dispatch(logout());
    window.location.href = "/";
  }
}

function App() {
  return (
    <Provider store={store}>
      <Router>
        <div className="App">
          <Header />

          {
            //Public Routes
          }
          <Route exact path="/" component={Landing} />

          <Route exact path="/register" component={Register} />

          <Route exact path="/login" component={Login} />

          {
            // Private Routes
          }

          <Switch>
            <SecureRoutes exact path="/dashboard" component={DashBoard} />

            <SecureRoutes exact path="/addProject" component={AddProject} />

            <SecureRoutes
              exact
              path="/updateProject/:id"
              component={UpdateProject}
            />

            <SecureRoutes
              exact
              path="/projectBoard/:id"
              component={ProjectBoard}
            />
            <SecureRoutes
              exact
              path="/addProjectTask/:id"
              component={AddProjectTask}
            />
            <SecureRoutes
              exact
              path="/updateProjectTask/:backlog_id/:pt_id"
              component={UpdateProjectTask}
            />
          </Switch>
        </div>
      </Router>
    </Provider>
  );
}

export default App;
