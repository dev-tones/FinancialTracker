import { useState } from "react";
import type { Login } from "../types/Login";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../contexts/useAuth";
import { apiFetch } from "../api/apiClient";
import { Button } from "@/components/ui/button";
import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";
import { Label } from "@/components/ui/label";
import { Input } from "@/components/ui/input";

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
      console.log("LOGIN TOKEN:", token);

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
    <div className="flex min-h-screen items-center justify-center">
      <Card className="shadow-2xl w-full max-w-sm">
        <CardHeader>
          <CardTitle>Login to your account</CardTitle>
          <CardDescription>Enter your email and password</CardDescription>
        </CardHeader>

        <form onSubmit={handleSubmit}>
          <CardContent className="grid grid-cols-[100px_1fr] items-center gap-x-4 gap-y-4">
            <Label htmlFor="email">Email:</Label>
            <Input
              id="email"
              name="email"
              className="shadow-lg hover:bg-slate-200"
              type="email"
              autoComplete="email"
              required
              value={formData.email}
              onChange={(e) =>
                setFormData({ ...formData, email: e.target.value })
              }
            />

            <Label htmlFor="password">Password:</Label>
            <Input
              id="password"
              name="password"
              className="shadow-lg hover:bg-slate-200"
              type="password"
              autoComplete="current-password"
              required
              value={formData.password}
              onChange={(e) =>
                setFormData({ ...formData, password: e.target.value })
              }
            />
          </CardContent>

          <CardFooter className="mt-2 flex flex-col items-center">
            {error && <p className="text-red-500">{error}</p>}

            <Button
              className="px-4 py-2 shadow-md"
              type="submit"
              disabled={loading}
            >
              {loading ? "Submitting..." : "Login"}
            </Button>
          </CardFooter>
        </form>
      </Card>
    </div>
  );
}
export default LoginPage;
