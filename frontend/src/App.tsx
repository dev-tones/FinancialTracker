import { useEffect, useState } from "react";
import { apiFetch } from "./api/apiClient";
import type { User } from "./types/User";

function App() {
  const [message, setMessage] = useState("Loading...");
  const [user, setUser] = useState<User | null>(null);

  useEffect(() => {
    Promise.all([
      apiFetch("/hello").then((res) => res.text()),
      apiFetch("/users/profile").then((res) => res.json()),
    ])
      .then(([helloData, userData]) => {
        setMessage(helloData);
        setUser(userData);
      })
      .catch((err) => {
        setMessage("Error connecting to backend: " + err.message);
      });
  }, []);

  return (
    <div
      style={{
        display: "flex",
        justifyContent: "center", // horizontal center
        alignItems: "center", // vertical center
        height: "100vh", // full viewport height
      }}
    >
      <div style={{ textAlign: "center" }}>
        <h1>React + Spring Boot</h1>
        <p>
          Message from Java: <strong>{message}</strong>
        </p>

        {user && (
          <div
            style={{
              marginTop: "20px",
              border: "1px solid black",
              padding: "10px",
            }}
          >
            <h3>User Profile</h3>
            <p>Name: {user.name}</p>
            <p>Role: {user.role}</p>
          </div>
        )}
      </div>
    </div>
  );
}

export default App;
