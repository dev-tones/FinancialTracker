import React, { createContext } from "react";

export type AuthContextType = {
  token: string | null;
  setToken: React.Dispatch<React.SetStateAction<string | null>>;
};
export const AuthContext = createContext<AuthContextType | null>(null);
