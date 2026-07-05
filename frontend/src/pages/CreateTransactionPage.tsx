import { useState } from "react";
import type { Transaction } from "../types/Transaction";
import { useAuth } from "../contexts/useAuth";
function CreateTransactionPage() {
  const { token } = useAuth();
  const [formData, setFormData] = useState<Transaction>({
    date: "",
    amount: 0,
    type: "",
    categoryId: 0,
    description: "",
    reoccurring: false,
  });
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const handleSubmit = async (e: React.SyntheticEvent) => {
    e.preventDefault();
    if (loading) return;
    setLoading(true);
    try {
      const response = await fetch(
        "http://localhost:8080/api/v1/transactions",
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`,
          },
          body: JSON.stringify(formData),
        },
      );
      if (!response.ok) {
        throw new Error(`Http error! Status: ${response.status}`);
      }
      const data = await response.json();
      setFormData(data);
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
  if (loading) return <p>Loading profile...</p>;
  if (error) return <p style={{ color: "red" }}>Error: {error}</p>;
  return (
    <form className="flex flex-col gap-1" onSubmit={handleSubmit}>
      <label className="flex items-center gap-1">
        <span className="w-56">Amount of Transaction:</span>
        <input
          className="w-50 focus:border-indigo-600 border focus:outline-hidden"
          type="number"
          value={formData.amount}
          onChange={(e) =>
            setFormData({ ...formData, amount: e.target.valueAsNumber })
          }
        />
      </label>
      <label className="flex items-center gap-1">
        <span className="w-56">Type of Transaction:</span>
        <input
          className="w-50 focus:border-indigo-600 border focus:outline-hidden"
          type="text"
          value={formData.type}
          onChange={(e) => setFormData({ ...formData, type: e.target.value })}
        />
      </label>
      <label className="flex items-center gap-1">
        <span className="w-56">Date of Transaction:</span>
        <input
          className="w-50 focus:border-indigo-600 border focus:outline-hidden"
          type="date"
          value={formData.date}
          onChange={(e) => setFormData({ ...formData, date: e.target.value })}
        />
      </label>
      <label className="flex items-center gap-1">
        <span className="w-56">Category of Transaction:</span>
        <input
          className="w-50 focus:border-indigo-600 border focus:outline-hidden"
          type="number"
          value={formData.categoryId}
          onChange={(e) =>
            setFormData({ ...formData, categoryId: e.target.valueAsNumber })
          }
        />
      </label>
      <label className="flex items-center gap-1">
        <span className="w-56">Description:</span>
        <input
          className="w-50 focus:border-indigo-600 border focus:outline-hidden"
          type="string"
          value={formData.description}
          onChange={(e) =>
            setFormData({ ...formData, description: e.target.value })
          }
        />
      </label>
      <label className="flex items-center gap-1">
        <span className="w-56">Reoccurring:</span>
        <input
          className="w-50 focus:border-indigo-600 border focus:outline-hidden"
          type="checkbox"
          checked={formData.reoccurring}
          onChange={(e) =>
            setFormData({ ...formData, reoccurring: e.target.checked })
          }
        />
      </label>
      <button
        className="mx-auto block rounded-md bg-indigo-600 px-4 py-2 text-white hover:bg-indigo-700"
        type="submit"
        disabled={loading}
      >
        {loading ? "Submitting..." : "Submit"}
      </button>
    </form>
  );
}
export default CreateTransactionPage;
