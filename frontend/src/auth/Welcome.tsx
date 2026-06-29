import { useState } from "react";

export default function Welcome() {
    const [formData, setFormData] = useState({
        userName: "",
        fistName: "",
        lastName: "",
        password: "",
        email: "",
        phone: "",
    })
    const [error, setError] = useState("");
    const [success, setSuccess] = useState("");
    
}