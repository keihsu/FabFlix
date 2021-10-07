import React, { Component } from "react";

import Idm from "../services/Idm";

import "../css/common.css";

class Login extends Component {
  state = {
    email: "",
    password: "",
    message: ""
  };

  handleSubmit = e => {
    e.preventDefault();

    const { handleLogIn } = this.props;
    const { email, password } = this.state;

    Idm.login(email, password)
      .then(response => {
        console.log(response);
        if (response["data"]["resultCode"] === 120){
          handleLogIn(email, response["data"]["session_id"]);
        }
        else{
          this.setState({password: "", message: response["data"]["message"]});
        }
      })
      .catch(error => console.log(error));
  };

  updateField = ({ target }) => {
    const { name, value } = target;

    this.setState({ [name]: value });
  };

  render() {
    const { email, password, message } = this.state;

    return (
      <div className="flex-container">
        <form className="form-container" onSubmit={this.handleSubmit}>
        <h1 className="form-title">Login</h1>
          <div>
            <input
              className="form-field"
              id="email"
              type="email"
              name="email"
              placeholder="E-mail"
              value={email}
              onChange={this.updateField}
              required
            />
          </div>
          <div>
          <input
            className="form-field"
            id="password"
            type="password"
            name="password"
            placeholder="Password"
            value={password}
            onChange={this.updateField}
            required
          />
          </div>
          <div>
            <button className="form-submit">Submit</button>
          </div>
          <div>
            <label className="form-message">{message}</label>
          </div>
        </form>
      </div>
    );
  }
}

export default Login;
