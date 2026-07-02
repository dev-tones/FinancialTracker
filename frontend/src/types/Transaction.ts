export interface Transaction {
    amount: number,
    type: string,
    date: string,
    categoryId: number,
    description: string,
    reoccurring: boolean,
}