export interface Transaction {
    id: number,
    amount: number,
    type: string,
    date: Date,
    category: number,
    description: string,
    reoccurring: boolean,
}