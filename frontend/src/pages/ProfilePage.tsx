import { useEffect, useState } from "react";
import { useAuth } from "../contexts/useAuth";
import type { User } from "../types/User";
import { apiFetch } from "../api/apiClient";
import {
  Table,
  TableBody,
  TableCaption,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";

function ProfilePage() {
  const { token } = useAuth();
  const email = localStorage.getItem("email");
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const [tableData, setTableData] = useState<User>({
    id: 0,
    userRole: "",
    // userName: "",
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
    <Table>
      <TableCaption>Profile</TableCaption>
      <TableHeader>
        <TableRow>
          <TableHead>Name</TableHead>
          <TableHead>Role</TableHead>
          <TableHead>Email</TableHead>
          <TableHead>Phone</TableHead>
        </TableRow>
      </TableHeader>
      <TableBody>
        <TableRow>
          <TableCell>
            {tableData.firstName} {tableData.lastName}
          </TableCell>
          <TableCell>{tableData.userRole}</TableCell>
          <TableCell>{tableData.email}</TableCell>
          <TableCell>{tableData.phone}</TableCell>
        </TableRow>
      </TableBody>
    </Table>
  );
}
export default ProfilePage;
