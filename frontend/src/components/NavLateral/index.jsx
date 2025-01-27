import { DirectionsCarOutlined } from "@mui/icons-material";
import { styled } from "@mui/material";
import ListItem from "@mui/material/ListItem";
import ListItemButton from "@mui/material/ListItemButton";
import ListItemIcon from "@mui/material/ListItemIcon";
import ListItemText from "@mui/material/ListItemText";
import * as React from "react";
import { Link } from "react-router-dom";

import { CustomDrawer } from "./styles";

export const CustomListItemButton = styled(ListItemButton)(() => ({
  ":hover": {
    backgroundColor: "#fff",
    width: "100%",
    maxWidth: "240px",
    borderRadius: "0 30px 30px 0",
    cursor: "pointer",
  },
}));

export const CustomListItemIcon = styled(ListItemIcon)(() => ({
  color: "#818181",
  minWidth: "36px",
}));

export default function NavLateral({
  mobileOpen,
}) {
  const listaItensBotao = [
    { text: "Veículos", link: "/veiculos", icon: <DirectionsCarOutlined /> },
  ];

  return (
    <CustomDrawer
      className={mobileOpen ? "" : "hidden"}
    >
      {listaItensBotao.map((item, index) => (
        <ListItem key={item.text} disablePadding>
          <Link
            to={item.link}
            style={{
              width: "100%",
            }}
          >
            <CustomListItemButton>
              <CustomListItemIcon>
                {item.icon}
              </CustomListItemIcon>
              <ListItemText
                primary={item.text}
                sx={{
                  color: "#414141",
                }}
              />
            </CustomListItemButton>
          </Link>
        </ListItem>
      ))}
    </CustomDrawer>
  );
}
