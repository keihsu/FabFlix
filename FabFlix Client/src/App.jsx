import React, { Component } from "react";
//import Cookies from "js-cookie";
import Axios from "axios";
import NavBar from "./NavBar";
import Content from "./Content";
import Idm from "./services/Idm";
import { withRouter } from "react-router-dom";

const localStorage = require("local-storage");

class App extends Component {
  state = {
    loggedIn: this.checkLoggedIn()
  };

  handleLogIn = (email, session_id) => {
    const { common } = Axios.defaults.headers;

    // Cookies.set("email", email);
    // Cookies.set("session_id", session_id);

    common["email"] = email;
    common["session_id"] = session_id;
    localStorage.set('email', email);
    localStorage.set('session_id', session_id);
    this.setState({loggedIn: true});
    this.props.history.push('/movies');
  };

  handleLogOut = () => {
    const { common } = Axios.defaults.headers;

    // Cookies.remove("email");
    // Cookies.remove("session_id");

    delete common["email"];
    delete common["session_id"];
    localStorage.remove('email');
    localStorage.remove('session_id');
    this.setState({loggedIn: false});
    this.props.history.push('/');
  };

  checkSession = () => {
    const { loggedIn } = this.state;
    if(loggedIn === true){
      const { common } = Axios.defaults.headers;
      common["email"] = localStorage.get('email');
      common["session_id"] = localStorage.get('session_id');
      Idm.session(common["email"], common["session_id"])
      .then(response => {
          console.log(response);
          if(response["data"]["resultCode"] !== 130){
            this.handleLogOut();
          }
          else{
            localStorage.set('session_id', response["data"]["session_id"]);
            common["session_id"] = localStorage.get('session_id');
          }
      })
      .catch(error => console.log(error));
    }
  }

  checkLoggedIn() {
    return (
      // Cookies.get("email") !== undefined &&
      // Cookies.get("session_id") !== undefined
      localStorage.get('email') !== undefined &&
      localStorage.get('email') !== null &&
      localStorage.get('session_id') !== undefined &&
      localStorage.get('session_id') !== null
    );
  }

  render() {
    const { loggedIn } = this.state;
    this.checkSession();
    return (
      <div className="app">
        <NavBar handleLogOut={this.handleLogOut} loggedIn={loggedIn} />
        <Content handleLogIn={this.handleLogIn} />
      </div>
    );
  }
}

export default withRouter(App);
