import React, { Component } from "react";
import Axios from 'axios';
import Billing from "../services/Billing";

const localStorage = require("local-storage");

class Cart extends Component {
  state = {
    items: [],
    message: "",
    cartEmpty: "",
    orderTotal: 0
  };

  handleSubmit = e => {
    e.preventDefault();
    const { movie_id, quantity } = this.state;
    const { common } = Axios.defaults.headers;
    common["email"] = localStorage.get('email');
    common["session_id"] = localStorage.get('session_id');
    Billing.cartInsert(common["email"], movie_id, quantity)
    .then(response => {
      this.setState({message: response["data"]["message"]})
    })
    .catch(error => console.log(error));
  };

  updateField = e => {
    const { name, value } = e.target;

    this.setState({ [name]: value });
  };

getCart = () =>{
    const { common } = Axios.defaults.headers;
    common["email"] = localStorage.get('email');
    common["session_id"] = localStorage.get('session_id');
    Billing.cartRetrieve(common["email"])
    .then(response => {
        if(response["data"]["resultCode"] === 3130){
            //this.setState({items: response["data"]["items"]});
            var total = 0;
            for(var i = 0; i < response["data"]["items"].length; i++){
                var unitPrice = response["data"]["items"][i]["unit_price"];
                var discount = response["data"]["items"][i]["discount"];
                var qty = response["data"]["items"][i]["quantity"];
                total += (unitPrice - (unitPrice * discount)) * qty;
            }
            this.setState({
                items: response["data"]["items"],
                orderTotal: total.toFixed(2)
            })
        }
        else{
            this.setState({
                items: [],
                cartEmpty: "Cart is Empty"
            });
        }
    })
    .catch(error => console.log(error));
};

updateItem = (movie_id) => {
    const { common } = Axios.defaults.headers;
    common["email"] = localStorage.get('email');
    common["session_id"] = localStorage.get('session_id');
    Billing.cartUpdate(common["email"], movie_id, document.getElementById("qty" + movie_id).value)
    .then(response => {
        this.setState({message: response["data"]["message"]})
    })
    .catch(error => console.log(error));
    this.getCart();
};

deleteItem = (movie_id) => {
    const { common } = Axios.defaults.headers;
    common["email"] = localStorage.get('email');
    common["session_id"] = localStorage.get('session_id');
    Billing.cartDelete(common["email"], movie_id)
    .then(response => {
        this.setState({message: response["data"]["message"]})
    })
    .catch(error => console.log(error));
    this.getCart();
};

clearCart = () => {
    const { common } = Axios.defaults.headers;
    common["email"] = localStorage.get('email');
    common["session_id"] = localStorage.get('session_id');
    Billing.cartClear(common["email"])
    .then(response => {
        this.setState({message: response["data"]["message"]})
    })
    .catch(error => console.log(error));
    this.getCart();
};

placeOrder = () => {
    const { common } = Axios.defaults.headers;
    common["email"] = localStorage.get('email');
    common["session_id"] = localStorage.get('session_id');
    Billing.orderPlace(common["email"])
    .then(response => {
        if(response["data"]["resultCode"] === 3400){
            window.location.href = response["data"]["approve_url"]
        }
        else{
            this.setState({message: response["data"]["message"]})
        }
    })
    .catch(error => console.log(error));
    this.getCart();
};

componentDidMount() {
    this.getCart();
  }

  render() {
    const { items, message, cartEmpty, orderTotal } = this.state;
    const cartItems = items.map((item, key) =>
      <tr key={item.movie_id}>
        <td><img src={"https://image.tmdb.org/t/p/w92" + item.poster_path} alt={item.title}></img></td>
        <td>{item.movie_title}</td>
        <td>{"$" + ((item.unit_price - (item.unit_price * item.discount)) * item.quantity).toFixed(2)}</td>
        <td className="qty-container">
            <input type="number" className="input-field" id={"qty" + item.movie_id } name="quantity" min="1" defaultValue={item.quantity} required/>
            <div className="qty-button-container">
                <button className="form-button" onClick = {() => this.updateItem(item.movie_id)}>Update</button>
                <button className="form-button" onClick = {() => this.deleteItem(item.movie_id)}>Delete</button>
            </div>
        </td>
      </tr>
    );

    return (
        <div>
            <div>
                <h1>{cartEmpty}</h1>
            </div>
            <div>
            { items.length !== 0 &&
                <div className="flex-container2">
                    <h1>Cart</h1>
                    <table className="result-table">
                        <thead>
                        <tr>
                            <th>Movie Image</th>
                            <th>Title</th>
                            <th>Price</th>
                            <th>Quantity</th>
                        </tr>
                        </thead>
                        <tbody>
                        {cartItems}
                        </tbody>
                    </table>
                    <div>
                        { message !== "" &&
                            <label className="form-message">{message}</label>
                        }
                    </div>
                    <label>Order Total: {orderTotal}</label>
                    <div className="button-container">
                        <button className="form-button" onClick = {() => this.clearCart()}>Clear Cart</button>
                        <button className="form-button" onClick = {() => this.placeOrder()}>Place Order</button>
                    </div>
                </div>
            }
            </div>
        </div>
    );
  }
}

export default Cart;
