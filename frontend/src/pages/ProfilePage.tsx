import { useState, useEffect } from "react";
import type { User } from "../types/User";
import { useNavigate } from "react-router-dom";

function ProfilePage() {
  const navigate = useNavigate();
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const [tableData, setTableData] = useState<User>({
    id: 0,
    userRole: "",
    userName: "",
    firstName: "",
    lastName: "",
    email: "",
    phone: "",
    createdAt: "",
    updatedAt: "",
  });

  useEffect(() => {
    const fetchUserData = async () => {
      const email = localStorage.getItem("email");
      if (!email) {
        setError("No email found.");
        setLoading(false);
        return;
      }
      try {
        const response = await fetch(
          `http://localhost:8080/api/v1/user/search?email=${email}`,
          {
            method: "GET",
            headers: {
              "Content-Type": "application/json",
              Authorization: `Bearer ${localStorage.getItem("token")}`,
            },
          },
        );
        if (!response.ok) {
          throw new Error(`HTTP Error! Status: ${response.status}`);
        }
        const data: User = await response.json();
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
    fetchUserData();
  }, []);

  if (loading) return <p>Loading profile...</p>;
  if (error) return <p style={{ color: "red" }}>Error: {error}</p>;
  return (
    <table>
      <tbody>
        <tr>
          <th>Name:</th>
          <td>
            {tableData.firstName} {tableData.lastName}
          </td>
        </tr>
        <tr>
          <th>Role:</th>
          <td> {tableData.userRole}</td>
        </tr>
        <tr>
          <th>Email:</th>
          <td> {tableData.email}</td>
        </tr>
        <tr>
          <th>Phone:</th>
          <td> {tableData.phone}</td>
        </tr>
      </tbody>
      <button onClick={() => navigate("/reoccurring")}>View Reoccurring</button>
    </table>
  );
}
export default ProfilePage;
