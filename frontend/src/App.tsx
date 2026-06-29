import Login from "./pages/Login";
import Profile from "./pages/Profile";
import Register from "./pages/Register";
import { BrowserRouter, Routes, Route, Link } from "react-router-dom";

function App() {
  return (
    <BrowserRouter>
      <nav>
        <Link to="/">Login</Link>
        <Link to="/register">Register</Link>
        <Link to="/profile">Profile</Link>
      </nav>

      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/profile" element={<Profile />} />
      </Routes>
    </BrowserRouter>
  );
}
export default App;
