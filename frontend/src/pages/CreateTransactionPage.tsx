import type { Transaction } from "../types/Transaction";
import { useState } from "react";
function CreateTransactionPage() {
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
        if(loading) return;
        setLoading(true);
        try {
            const response = await fetch('api/v1/transactions', {
                method: 'POST',
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer + ${localStorage.getItem("token")}`,
                },
                body: JSON.stringify(formData),    
            });
            if(!response.ok){
                throw new Error(`Http error! Status: ${response.status}`)
            }
            const data = await response.json();
            setFormData(data);
                   
        } catch(err) {
            if(err instanceof Error){
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
    return(
        <form onSubmit={handleSubmit}>
            <label>
                Amount of Transaction:
                <input
                type="number"
                value={formData.amount}
                onChange={(e) => 
                    setFormData({...formData, amount: e.target.valueAsNumber})
                }
                />
            </label>
            <label>
                Type of Transaction:
                <input
                type="text"
                value={formData.type}
                onChange={(e) =>
                    setFormData({...formData, type: e.target.value})
                }
                />
            </label>
            <label>
                Date of Transaction:
                <input
                type="date"
                value={formData.date}
                onChange={(e) => 
                    setFormData({...formData, date: e.target.value})
                }
                />
            </label>
            <label>
                Category of Transaction
                <input
                type="number"
                value={formData.categoryId}
                onChange={(e) =>
                    setFormData({...formData, categoryId: e.target.valueAsNumber})
                }
                />
            </label>
            <label>
                Description: 
                <input
                type="string"
                value={formData.description}
                onChange={(e) =>
                    setFormData({...formData, description: e.target.value})
                }
                />
            </label>
            <label>
                Reoccurring:
                <input
                type="checkbox"
                checked={formData.reoccurring}
                onChange={(e) =>
                    setFormData({...formData, reoccurring: e.target.checked})
                }
                />
            </label>
        </form>
    );
}
export default CreateTransactionPage;