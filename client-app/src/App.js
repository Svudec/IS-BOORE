import { Outlet } from "react-router-dom";
import Login from "./components/login";

function App() {
  return (
    <div className="container">
      {//TODO Tu ide navbar
      }

      <Outlet />
    </div>
  );
}

export default App;