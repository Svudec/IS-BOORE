import axios from "axios";
import AuthService from "./AuthService";

export function authHeader() {
    const user = AuthService.getCurrentUser();
    if (user && user.accessToken) {
        return { Authorization: 'Bearer ' + user.accessToken };
    } else {
        return {};
    }
}

class UserService {
    getAPI(url){
        return axios.get(url, {headers: authHeader()});
    }

    postAPI(url, body){
        return axios.post(url, body, {headers: authHeader()});
    }

    putAPI(url, body){
        return axios.put(url, body, {headers: authHeader()});
    }

    deleteAPI(url){
        return axios.delete(url, {headers: authHeader()});
    }
  }
  export default new UserService();