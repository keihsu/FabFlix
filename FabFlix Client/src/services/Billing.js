import Socket from "../util/Socket";
import { billingEPs } from "../Config.json";

const { cartInsertEP, cartUpdateEP, cartDeleteEP, cartRetrieveEP, cartClearEP, orderPlaceEP, orderRetrieveEP, orderCompleteEP} = billingEPs;

async function cartInsert(email, movie_id, quantity) {
    const payLoad = {
      email: email,
      movie_id: movie_id,
      quantity: parseInt(quantity)
    };
    console.log(cartInsertEP);
    return await Socket.POST(cartInsertEP, payLoad);
}

async function cartUpdate(email, movie_id, quantity) {
    const payLoad = {
      email: email,
      movie_id: movie_id,
      quantity: parseInt(quantity)
    };
  
    return await Socket.POST(cartUpdateEP, payLoad);
}

async function cartDelete(email, movie_id) {
    const payLoad = {
      email: email,
      movie_id: movie_id
    };
  
    return await Socket.POST(cartDeleteEP, payLoad);
}

async function cartRetrieve(email) {
    const payLoad = {
      email: email
    };
  
    return await Socket.POST(cartRetrieveEP, payLoad);
}

async function cartClear(email) {
    const payLoad = {
      email: email
    };
  
    return await Socket.POST(cartClearEP, payLoad);
}

async function orderPlace(email) {
    const payLoad = {
      email: email
    };
  
    return await Socket.POST(orderPlaceEP, payLoad);
}

async function orderRetrieve(email) {
    const payLoad = {
      email: email
    };
  
    return await Socket.POST(orderRetrieveEP, payLoad);
}


async function orderComplete(path) {
    const p = orderCompleteEP + path;
    console.log(p);
    return await Socket.GET(p);
}


export default { cartInsert, cartUpdate, cartDelete, cartRetrieve, cartClear, orderPlace, orderRetrieve, orderComplete };