import { useState } from "react";
import { useNavigate } from "react-router-dom";
import type { Register } from "../types/Register";

function Register() {
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const [formData, setFormData] = useState<Register>({
    userName: "",
    firstName: "",
    lastName: "",
    email: "",
    phone: "",
    password: "",
  });
  const navigate = useNavigate();
  const handleSubmit = async (e: React.SyntheticEvent) => {
    e.preventDefault();
    setError("");
    if (loading) return;
    setLoading(true);
    try {
      const response = await fetch(
        "http://localhost:8080/api/v1/auth/register",
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(formData),
        },
      );
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      navigate("/login");
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
    <form onSubmit={handleSubmit}>
      <label>
        User Name:
        <input
          type="text"
          value={formData.userName}
          onChange={(e) =>
            setFormData({ ...formData, userName: e.target.value })
          }
        />
      </label>
      <label>
        First Name:
        <input
          type="text"
          value={formData.firstName}
          onChange={(e) =>
            setFormData({ ...formData, firstName: e.target.value })
          }
        />
      </label>
      <label>
        Last Name:
        <input
          type="text"
          value={formData.lastName}
          onChange={(e) =>
            setFormData({ ...formData, lastName: e.target.value })
          }
        />
      </label>
      <label>
        Email:
        <input
          type="text"
          value={formData.email}
          onChange={(e) => setFormData({ ...formData, email: e.target.value })}
        />
      </label>
      <label>
        Phone:
        <input
          type="text"
          value={formData.phone}
          onChange={(e) => setFormData({ ...formData, phone: e.target.value })}
        />
      </label>
      <label>
        Password:
        <input
          type="password"
          value={formData.password}
          onChange={(e) =>
            setFormData({ ...formData, password: e.target.value })
          }
        />
      </label>
      {error && <p style={{ color: "red" }}>{error}</p>}
      <button type="submit" disabled={loading}>
        {loading ? "Submitting..." : "Submit"}
      </button>
    </form>
  );
}
export default Register;
