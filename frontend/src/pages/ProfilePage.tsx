import { useEffect, useState } from "react";
import { useAuth } from "../contexts/useAuth";
import type { User } from "../types/User";
import { apiFetch } from "../api/apiClient";

function ProfilePage() {
  const { token } = useAuth();
  const email = localStorage.getItem("email");
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
      if (!email) {
        setError("No email found.");
        setLoading(false);
        return;
      }
      try {
        setLoading(true);
        const response = await apiFetch(`/user/search?email=${email}`, {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`,
          },
        });
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
  }, [token, email]);

  if (loading) return <p>Loading profile...</p>;
  if (error) return <p style={{ color: "red" }}>Error: {error}</p>;
  return (
    <div className="pt-5">
      <table className="table-auto border-collapse">
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
      </table>
    </div>
  );
}
export default ProfilePage;
