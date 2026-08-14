import { useState } from "react";
import type { Login } from "../types/Login";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../contexts/useAuth";
import { apiFetch } from "../api/apiClient";

function LoginPage() {
  const navigate = useNavigate();
  const { setToken } = useAuth();
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const [formData, setFormData] = useState<Login>({
    email: "",
    password: "",
  });
  const handleSubmit = async (e: React.SyntheticEvent) => {
    e.preventDefault();
    setError("");
    if (loading) return;
    setLoading(true);
    try {
      const response = await apiFetch("/auth/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(formData),
      });
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      const token = await response.text();
      localStorage.setItem("token", token);
      localStorage.setItem("email", formData.email);
      setToken(token);
      navigate("/profile");
    } catch (err) {
      if (err instanceof Error) {
        setError(err.message);
      } else {
        setError("Something went wrong");
      }
    } finally {
      setLoading(false);
    }
  };
  return (
    <form onSubmit={handleSubmit} className="flex flex-col items-center gap-4">
      <label className="flex flex-col pt-4">
        Email:
        <input
          className="shadow-lg hover:bg-slate-200 flex-1"
          type="text"
          value={formData.email}
          onChange={(e) => setFormData({ ...formData, email: e.target.value })}
        />
      </label>
      <label className="flex flex-col">
        Password:
        <input
          className="shadow-lg hover:bg-slate-200 flex-1"
          type="password"
          value={formData.password}
          onChange={(e) =>
            setFormData({ ...formData, password: e.target.value })
          }
        />
      </label>
      {error && <p style={{ color: "red" }}>{error}</p>}
      <button
        className="mt-2 rounded-lg shadow-md bg-green-300 px-4 py-2 hover:bg-green-500"
        type="submit"
        disabled={loading}
      >
        {loading ? "Submitting..." : "Submit"}
      </button>
    </form>
  );
}
export default LoginPage;
