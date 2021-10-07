import React, { Component } from "react";
import { Route, Switch } from "react-router-dom";

import Login from "./pages/Login";
import Register from "./pages/Register";
import Movies from "./pages/Movies";
import Details from "./pages/Details";
import Orders from "./pages/Orders";
import Cart from "./pages/Cart";
import Complete from "./pages/Complete";
import Home from "./pages/Home";

class Content extends Component {
  render() {
    const { handleLogIn } = this.props;

    return (
      <div className="content">
        <Switch>
          <Route
            path="/login"
            component={props => <Login handleLogIn={handleLogIn} {...props} />}
          />
          <Route path="/register" component={Register} />
          <Route path="/movies" component={Movies} />
          <Route path="/details" component={Details} />
          <Route path="/orders" component={Orders} />
          <Route path="/cart" component={Cart} />
          <Route path="/complete" component={Complete} />
          <Route path="/" component={Home} />
        </Switch>
      </div>
    );
  }
}

export default Content;
