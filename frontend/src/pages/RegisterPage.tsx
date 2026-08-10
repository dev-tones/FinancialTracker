import { useState } from "react";
import { useNavigate } from "react-router-dom";
import type { Register } from "../types/Register";

function RegisterPage() {
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
    <form className="grid grid-cols-6" onSubmit={handleSubmit}>
      <label className="col-span-4 col-start-2 flex items-center gap-2">
        <span className="w-32">User Name:</span>
        <input
          className="shadow-lg hover:bg-slate-200 flex-1"
          type="text"
          value={formData.userName}
          onChange={(e) =>
            setFormData({ ...formData, userName: e.target.value })
          }
        />
      </label>
      <label className="col-span-4 col-start-2 flex items-center gap-2">
        <span className="w-32">First Name:</span>
        <input
          className="shadow-lg hover:bg-slate-200 flex-1"
          type="text"
          value={formData.firstName}
          onChange={(e) =>
            setFormData({ ...formData, firstName: e.target.value })
          }
        />
      </label>
      <label className="col-span-4 col-start-2 flex items-center gap-2">
        <span className="w-32">Last Name:</span>
        <input
          className="shadow-lg hover:bg-slate-200 flex-1"
          type="text"
          value={formData.lastName}
          onChange={(e) =>
            setFormData({ ...formData, lastName: e.target.value })
          }
        />
      </label>
      <label className="col-span-4 col-start-2 flex items-center gap-2">
        <span className="w-32">Email:</span>
        <input
          className="shadow-lg hover:bg-slate-200 flex-1"
          type="text"
          value={formData.email}
          onChange={(e) => setFormData({ ...formData, email: e.target.value })}
        />
      </label>
      <label className="col-span-4 col-start-2 flex items-center gap-2">
        <span className="w-32">Phone:</span>
        <input
          className="shadow-lg hover:bg-slate-200 flex-1"
          type="text"
          value={formData.phone}
          onChange={(e) => setFormData({ ...formData, phone: e.target.value })}
        />
      </label>
      <label className="col-span-4 col-start-2 flex items-center gap-2">
        <span className="w-32">Password:</span>
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
        className="mt-2 rounded-lg bg-green-300 hover:bg-green-500 col-span-1 col-start-2"
        type="submit"
        disabled={loading}
      >
        {loading ? "Submitting..." : "Submit"}
      </button>
    </form>
  );
}
export default RegisterPage;
