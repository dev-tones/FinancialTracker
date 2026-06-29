import { useState, useEffect } from "react";
import type { User } from "../types/User";

function Profile() {
  const [ loading, setLoading ] = useState(false);
  const [ error, setError ] = useState("");
  const [ tableData, setTableData ] = useState<User>({
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
  if(!email){
    setError("No email found.");
    setLoading(false);
    return;
  }
  try {
    const response = await fetch(`http://localhost:8080/api/v1/search?email=${email}`,
      {
        method: "GET",
        headers: 
        {
          "Content-Type": "application/json"
        }
      });
    if(!response.ok){
      throw new Error(`HTTP Error! Status: ${response.status}`);
    }
    const data: User = await response.json();
    setTableData(data);
  } catch (err) {
      if(err instanceof Error){
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
  
if(loading) return <p>Loading profile...</p>;
if(error) return <p style={{ color: 'red' }}>Error: {error}</p>; 
  return (
    <table>
      <tbody>
      <tr>
        Name:
        <td>
          {tableData.firstName} {tableData.lastName}
        </td>
      </tr>
      <tr>
        <td>
          {tableData.userRole}
        </td>
      </tr>
      <tr>
        <td>
          {tableData.email}
        </td>
      </tr>
      <tr>
        <td>
          {tableData.phone}
        </td>
      </tr>
      </tbody>
    </table>
  );
}
export default Profile;
