import { BrowserRouter, Route, Routes } from "react-router-dom";
import AppSidebar from "./components/AppSidebar";
import { ProtectedRoute } from "./components/ProtectedRoute";
import { AuthProvider } from "./contexts/AuthProvider";
import CreateTransactionPage from "./pages/CreateTransactionPage";
import LoginPage from "./pages/LoginPage";
import ProfilePage from "./pages/ProfilePage";
import RegisterPage from "./pages/RegisterPage";
import { SidebarProvider, SidebarTrigger } from "./components/ui/sidebar";

function App() {
  return (
    <AuthProvider>
      <BrowserRouter>
        <SidebarProvider>
          <AppSidebar />

          <main className="flex-1">
            <SidebarTrigger />

            <Routes>
              <Route path="/" element={<LoginPage />} />
              <Route path="/register" element={<RegisterPage />} />

              <Route
                path="/profile"
                element={
                  <ProtectedRoute>
                    <ProfilePage />
                  </ProtectedRoute>
                }
              />

              <Route
                path="/create-tx"
                element={
                  <ProtectedRoute>
                    <CreateTransactionPage />
                  </ProtectedRoute>
                }
              />
            </Routes>
          </main>
        </SidebarProvider>
      </BrowserRouter>
    </AuthProvider>
  );
}

export default App;
