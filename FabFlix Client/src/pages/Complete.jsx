import React, { Component } from "react";
import Axios from 'axios';
import Billing from "../services/Billing";

const localStorage = require("local-storage");

class Complete extends Component {
  state = {
      message: ""
  };


  updateField = e => {
    const { name, value } = e.target;

    this.setState({ [name]: value });
  };

  handleSubmit = () => {
    const { common } = Axios.defaults.headers;
    common["email"] = localStorage.get('email');
    common["session_id"] = localStorage.get('session_id');
    console.log(common["email"])
    console.log(common["session_id"])
    Billing.orderComplete(this.props.location.search)
    .then(response => {
        console.log(response);
        this.setState({message: response["data"]["message"]});
    })
    .catch(error => console.log(error));
  }

componentDidMount() {
    this.handleSubmit();
}

  render() {
    const { message } = this.state;

    return (
        <h1>{message}</h1>
    );
  }
}

export default Complete;
