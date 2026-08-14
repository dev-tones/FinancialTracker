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
    <nav className="flex gap-4 p-4 border-b border-solid bg-green-300">
      {token ? (
        <>
          <Link to="/profile">Profile</Link>
          <Link to="/create-tx" className="hover:bg-green-500">
            Create Transaction
          </Link>
          <button onClick={handleLogout}>Logout</button>
        </>
      ) : (
        <>
          <Link to="/" className="rounded-md p-2 hover:bg-green-500">
            Login
          </Link>
          <Link to="/register" className="rounded-md p-2 hover:bg-green-500">
            Register
          </Link>
        </>
      )}
    </nav>
  );
}
export default Navbar;
