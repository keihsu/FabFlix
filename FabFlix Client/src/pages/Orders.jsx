import React, { Component } from "react";
import Axios from 'axios';
import Billing from "../services/Billing";

const localStorage = require("local-storage");

class Orders extends Component {
  state = {
    transactions: [],
    message: "",
    noOrders: ""
  };

getOrders = () =>{
    const { common } = Axios.defaults.headers;
    common["email"] = localStorage.get('email');
    common["session_id"] = localStorage.get('session_id');
    Billing.orderRetrieve(common["email"])
    .then(response => {
        if(response["data"]["resultCode"] === 3410){
            this.setState({transactions: response["data"]["transactions"]});
        }
        else{
            this.setState({transactions: []});
            this.setState({noOrders: "No Orders Found"});
        }
    })
    .catch(error => console.log(error));
};

componentDidMount() {
    this.getOrders();
  }

  render() {
    const { transactions, message, noOrders} = this.state;
    const orderHistory = transactions.map((item, key) =>
      <tr key={item.capture_id}>
        <td>{item.items[0].sale_date}</td>
        <td>{"$" + item.amount.total}</td>
        <td>{item.items[0].quantity}</td>
      </tr>
    );

    return (
        <div>
            <div>
                <h1>{noOrders}</h1>
            </div>
            <div>
            { transactions.length !== 0 &&
                <div className="flex-container2">
                    <h1>Orders</h1>
                    <table className="result-table">
                        <thead>
                        <tr>
                            <th>Sale Date</th>
                            <th>Sale Amount</th>
                            <th>Order Quantity</th>
                        </tr>
                        </thead>
                        <tbody>
                        {orderHistory}
                        </tbody>
                    </table>
                    <div>
                        { message !== "" &&
                            <label className="form-message">{message}</label>
                        }
                    </div>
                </div>
            }
            </div>
        </div>
    );
  }
}

export default Orders;
