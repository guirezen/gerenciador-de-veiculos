import { useState } from "react";
import { Outlet } from "react-router-dom";

import Header from "../Header";
import { Conteiner } from "./styles";

export default function Layout() {

  return (
    <div>
      <Header />
      <Conteiner>
        <Outlet />
      </Conteiner>
    </div>
  );
}
