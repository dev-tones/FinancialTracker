import { useEffect, useState } from "react";
import type { Reoccurring } from "../types/Reoccurring";

function ReoccurringList() {
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);
  const [tableData, setTableData] = useState<Reoccurring>({
    id: 0,
    amount: 0,
    type: "",
    categoryId: 0,
    description: "",
    reoccurring: true,
  });
  useEffect(() => {
    const fetchReoccurring = async () => {
      try {
        const response = await fetch("/api/v1/transactions/reoccurance", {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${localStorage.getItem("token")}`,
          },
        });
        if (!response.ok) {
          throw new Error(`HTTP Error! Status: ${response.status}`);
        }
        const data: Reoccurring = await response.json();
        setTableData(data);
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
    fetchReoccurring();
  }, []);
  if (loading) return <p>Loading profile...</p>;
  if (error) return <p style={{ color: "red" }}>Error: {error}</p>;
  return (
    <table>
      <tbody>
        <tr>
          <th>Transaction Id: </th>
          <td>{tableData.id}</td>
        </tr>
        <tr>
          <th>Amount: </th>
          <td>{tableData.amount}</td>
        </tr>
        <tr>
          <th>Description: </th>
          <td>{tableData.description}</td>
        </tr>
      </tbody>
    </table>
  );
}
export default ReoccurringList;
