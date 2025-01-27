import { useState } from "react";
import { Outlet } from "react-router-dom";

import Header from "../Header";
import NavLateral from "../NavLateral";
import { Conteiner } from "./styles";

export default function Layout() {
  const [mobileOpen, setMobileOpen] = useState(true);
  const [isClosing, setIsClosing] = useState(false);

  const handleDrawerClose = () => {
    setIsClosing(true);
    setMobileOpen(false);
  };

  const handleDrawerTransitionEnd = () => {
    setIsClosing(false);
  };

  const handleDrawerToggle = () => {
    if (!isClosing) {
      setMobileOpen(!mobileOpen);
    }
  };

  return (
    <div>
      <Header handleDrawerToggle={handleDrawerToggle} />
      <Conteiner>
        <NavLateral
          mobileOpen={mobileOpen}
          handleDrawerClose={handleDrawerClose}
          handleDrawerTransitionEnd={handleDrawerTransitionEnd}
        />
        <Outlet />
      </Conteiner>
    </div>
  );
}
