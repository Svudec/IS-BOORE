import axios from "axios";
import { LOGIN, REGISTER } from "./Routes";

class AuthService {
  async login(loginObj) {
    const response = await axios
          .post(LOGIN, loginObj);
      if (response.data.accessToken) {
          localStorage.setItem("user", JSON.stringify(response.data));
      }
      return response.data;
  }
  logout() {
    localStorage.removeItem("user");
  }
  async register(registerObj) {
    const response = await axios.post(REGISTER, registerObj);
    if (response.data.accessToken) {
        localStorage.setItem("user", JSON.stringify(response.data));
    }
    return response.data;
  }
  getCurrentUser() {
    return JSON.parse(localStorage.getItem('user'));
  }
}
export default new AuthService();