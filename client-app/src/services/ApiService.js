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
    getAPI()
  }
  export default new UserService();