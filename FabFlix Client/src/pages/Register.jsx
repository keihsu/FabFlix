import React, { Component } from "react";

import Idm from "../services/Idm";

import "../css/style.css";

/*
  Using localStorage is similar to how we use
  dictionarys. 
  
  To set a variable call `localStorage.set("key", value)`
  To get a variable call `localStorage.get("key")`

  Local Storage persists through website refreshes so
  it is perfect for storing things we dont want to lose
  like a users session

  You must call `const localStorage = require("local-storage");`
  in any class that you want to use this in, it is the same
  local storage in the entire website regardless of where you call
  it as each website gets the same instance of the storage.

  So think of it as a global dictionary.
*/
//const localStorage = require("local-storage");

class Register extends Component {
  state = {
    email: "",
    password: "",
    passwordConfirm: "",
    message: ""
  };

  handleSubmit = e => {
    e.preventDefault();

    //const { handleLogIn } = this.props;
    const { email, password, passwordConfirm } = this.state;
    if(password !== passwordConfirm){
        this.setState({message: "Passwords do not match."});
    }
    else{
        Idm.register(email, password)
        .then(response => {
            console.log(response);
            if (response["data"]["resultCode"] === 110){
                //handleLogIn(email, response["data"]["session_id"]);
                this.setState({message: "User registered Successfully"})
            }
            else{
                this.setState({message: response["data"]["message"]});
            }
        })
        .catch(error => alert(error));
    }
  };

  updateField = e => {
    const { name, value } = e.target;

    this.setState({ [name]: value });
  };

  render() {
    const { email, password, passwordConfirm, message } = this.state;

    return (
      <div className="flex-container">
      <form className="form-container" onSubmit={this.handleSubmit}>
      <h1 className="form-title">Sign Up!</h1>
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
        <input
          className="form-field"
          id="passwordConfirm"
          type="password"
          name="passwordConfirm"
          placeholder="Re-enter Password"
          value={passwordConfirm}
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

export default Register;
