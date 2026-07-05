import { Link, useNavigate } from "react-router-dom";
import { useAuth } from "../contexts/useAuth";

function Navbar() {
  const { token, setToken } = useAuth();
  const navigate = useNavigate();

  function handleLogout() {
    localStorage.removeItem("token");
    localStorage.removeItem("email");
    setToken(null);
    navigate("/");
  }

  return (
    <nav className="flex gap-4 p-4 border-b border-solid">
      {token ? (
        <>
          <Link to="/profile">Profile</Link>
          <Link to="/create-tx">Create Transaction</Link>
          <button onClick={handleLogout}>Logout</button>
        </>
      ) : (
        <>
          <Link to="/">Login</Link>
          <Link to="/register">Register</Link>
        </>
      )}
    </nav>
  );
}
export default Navbar;
