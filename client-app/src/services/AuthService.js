import axios from "axios";
import Routes from "./Routes";

class AuthService {
  login(username, password) {
    return axios
      .post(Routes.LOGIN, {
        username,
        password
      })
      .then(response => {
        if (response.data.accessToken) {
          localStorage.setItem("user", JSON.stringify(response.data));
        }
        return response.data;
      });
  }
  logout() {
    localStorage.removeItem("user");
  }
  register(registerObj) {
    return axios.post(Routes.REGISTER, registerObj);
  }
  getCurrentUser() {
    return JSON.parse(localStorage.getItem('user'));;
  }
}
export default new AuthService();