import React, { Component } from "react";
import "./App.css";
import Dashboard from "./components/Dashboard";
import Header from "./components/Layout/Header";
import "bootstrap/dist/css/bootstrap.min.css";
import {
  HashRouter as Router,
  Route,
  Switch,
  Redirect
} from "react-router-dom";
import AddProject from "./components/Project/AddProject";
import { Provider } from "react-redux";
import store from "./store";
import UpdateProject from "./components/Project/UpdateProject";
import ProjectBoard from "./components/ProjectBoard/ProjectBoard";
import AddProjectTask from "./components/ProjectBoard/ProjectTasks/AddProjectTask";
import UpdateProjectTask from "./components/ProjectBoard/ProjectTasks/UpdateProjectTask";
import Landing from "./components/Layout/Landing";
import Register from "./components/UserManagement/Register";
import Login from "./components/UserManagement/Login";
import jwt_decode from "jwt-decode";
import setJWTToken from "./securityUtils/setJWTToken";
import { SET_CURRENT_USER } from "./actions/types";
import { logout } from "./actions/securityActions";
import SecuredRoute from "./securityUtils/SecureRoute";
import Footer from "./components/Layout/Footer";

const jwtToken = localStorage.jwtToken;

if (jwtToken) {
  setJWTToken(jwtToken);
  const decoded_jwtToken = jwt_decode(jwtToken);
  store.dispatch({
    type: SET_CURRENT_USER,
    payload: decoded_jwtToken
  });

  const currentTime = Date.now() / 1000;
  if (decoded_jwtToken.exp < currentTime) {
    store.dispatch(logout());
    window.location.href = "/";
  }
}

class App extends Component {
  render() {
    return (
      <Provider store={store}>
        <Router>
          <div className="App my-div">
            <div className="my-div-height">
              <Header />
              <div className="my-div-height">
                {
                  //Public Routes
                }
                <Switch>
                  <Route exact path="/" component={Landing} />
                  <Route exact path="/register" component={Register} />
                  <Route exact path="/login" component={Login} />
                  <Route
                    path="/"
                    render={() => (
                      <Switch>
                        <SecuredRoute path="/dashboard" component={Dashboard} />
                        <SecuredRoute
                          path="/addProject"
                          component={AddProject}
                        />
                        <SecuredRoute
                          path="/updateProject/:id"
                          component={UpdateProject}
                        />
                        <SecuredRoute
                          path="/projectBoard/:id"
                          component={ProjectBoard}
                        />
                        <SecuredRoute
                          path="/addProjectTask/:id"
                          component={AddProjectTask}
                        />
                        <SecuredRoute
                          path="/updateProjectTask/:backlog_id/:pt_id"
                          component={UpdateProjectTask}
                        />
                        <Redirect to="/" />
                      </Switch>
                    )}
                  />
                </Switch>
                {
                  //Private Routes
                }
              </div>
            </div>
            <Footer />
          </div>
        </Router>
      </Provider>
    );
  }
}

export default App;
