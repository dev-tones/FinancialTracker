import {
  Sidebar,
  SidebarContent,
  SidebarFooter,
  SidebarGroup,
  SidebarGroupContent,
  SidebarHeader,
  SidebarMenu,
  SidebarMenuButton,
  SidebarMenuItem,
} from "@/components/ui/sidebar";

import {
  CircleUserRound,
  LogIn,
  LogOut,
  ReceiptText,
  UserPlus,
  WalletCards,
} from "lucide-react";

import { Link, useNavigate } from "react-router-dom";
import { useAuth } from "../contexts/useAuth";

function AppSidebar() {
  const { token, setToken } = useAuth();
  const navigate = useNavigate();

  console.log("SIDEBAR TOKEN:", token);

  function handleLogout() {
    localStorage.removeItem("token");
    localStorage.removeItem("email");
    setToken(null);
    navigate("/");
  }

  return (
    <Sidebar collapsible="icon">
      <SidebarHeader>
        <SidebarMenu>
          <SidebarMenuItem>
            <SidebarMenuButton tooltip="Financial Tracker">
              <WalletCards />
              <span>Financial Tracker</span>
            </SidebarMenuButton>
          </SidebarMenuItem>
        </SidebarMenu>
      </SidebarHeader>

      <SidebarContent>
        <SidebarGroup>
          <SidebarGroupContent>
            <SidebarMenu>
              {token ? (
                <>
                  <SidebarMenuItem>
                    <SidebarMenuButton
                      tooltip="Profile"
                      render={<Link to="/profile" />}
                    >
                      <CircleUserRound />
                      <span>Profile</span>
                    </SidebarMenuButton>
                  </SidebarMenuItem>

                  <SidebarMenuItem>
                    <SidebarMenuButton
                      tooltip="Create Transaction"
                      render={<Link to="/create-tx" />}
                    >
                      <ReceiptText />
                      <span>Create Transaction</span>
                    </SidebarMenuButton>
                  </SidebarMenuItem>

                  <SidebarMenuItem>
                    <SidebarMenuButton tooltip="Logout" onClick={handleLogout}>
                      <LogOut />
                      <span>Logout</span>
                    </SidebarMenuButton>
                  </SidebarMenuItem>
                </>
              ) : (
                <>
                  <SidebarMenuItem>
                    <SidebarMenuButton tooltip="Login" render={<Link to="/" />}>
                      <LogIn />
                      <span>Login</span>
                    </SidebarMenuButton>
                  </SidebarMenuItem>

                  <SidebarMenuItem>
                    <SidebarMenuButton
                      tooltip="Register"
                      render={<Link to="/register" />}
                    >
                      <UserPlus />
                      <span>Register</span>
                    </SidebarMenuButton>
                  </SidebarMenuItem>
                </>
              )}
            </SidebarMenu>
          </SidebarGroupContent>
        </SidebarGroup>
      </SidebarContent>

      <SidebarFooter />
    </Sidebar>
  );
}

export default AppSidebar;
