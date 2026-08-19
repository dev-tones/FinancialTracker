import { useState } from "react";
import { useNavigate } from "react-router-dom";
import type { Register } from "../types/Register";
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
import { apiFetch } from "@/api/apiClient";

function RegisterPage() {
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const [formData, setFormData] = useState<Register>({
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
      const response = await apiFetch("/auth/register", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(formData),
      });
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      navigate("/");
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
          <CardTitle>Register for an account</CardTitle>
          <CardDescription>
            Enter your information below to create your account
          </CardDescription>
        </CardHeader>

        <form onSubmit={handleSubmit}>
          <CardContent className="grid grid-cols-[100px_1fr] items-center gap-x-4 gap-y-4">
            <Label htmlFor="firstName">First Name:</Label>
            <Input
              id="firstName"
              name="firstName"
              type="text"
              autoComplete="given-name"
              required
              className="shadow-lg hover:bg-slate-200"
              value={formData.firstName}
              onChange={(e) =>
                setFormData({ ...formData, firstName: e.target.value })
              }
            />

            <Label htmlFor="lastName">Last Name:</Label>
            <Input
              id="lastName"
              name="lastName"
              type="text"
              autoComplete="family-name"
              required
              className="shadow-lg hover:bg-slate-200"
              value={formData.lastName}
              onChange={(e) =>
                setFormData({ ...formData, lastName: e.target.value })
              }
            />

            <Label htmlFor="email">Email:</Label>
            <Input
              id="email"
              name="email"
              type="email"
              autoComplete="email"
              required
              className="shadow-lg hover:bg-slate-200"
              value={formData.email}
              onChange={(e) =>
                setFormData({ ...formData, email: e.target.value })
              }
            />

            <Label htmlFor="phone">Phone:</Label>
            <Input
              id="phone"
              name="phone"
              type="tel"
              autoComplete="tel"
              className="shadow-lg hover:bg-slate-200"
              value={formData.phone}
              onChange={(e) =>
                setFormData({ ...formData, phone: e.target.value })
              }
            />

            <Label htmlFor="password">Password:</Label>
            <Input
              id="password"
              name="password"
              type="password"
              autoComplete="new-password"
              required
              className="shadow-lg hover:bg-slate-200"
              value={formData.password}
              onChange={(e) =>
                setFormData({ ...formData, password: e.target.value })
              }
            />
          </CardContent>

          <CardFooter className="mt-2 flex flex-col items-center gap-2">
            {error && <p className="text-sm text-red-500">{error}</p>}

            <Button className="shadow-md" type="submit" disabled={loading}>
              {loading ? "Submitting..." : "Register"}
            </Button>
          </CardFooter>
        </form>
      </Card>
    </div>
  );
}
export default RegisterPage;
