import Socket from "../util/Socket";
import { idmEPs } from "../Config.json";

const { loginEP, registerEP, sessionEP } = idmEPs;

async function login(email, password) {
  const payLoad = {
    email: email,
    password: password.split("")
  };

  return await Socket.POST(loginEP, payLoad);
}

async function register(email, password) {
  const payLoad = {
    email: email,
    password: password.split("")
  };

  return await Socket.POST(registerEP, payLoad);
}

async function session(email, session_id) {
  const payLoad = {
    email: email,
    session_id: session_id
  };

  return await Socket.POST(sessionEP, payLoad);
}

export default {
  login,
  register,
  session
};
