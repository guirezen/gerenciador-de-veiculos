import { Menu } from "@mui/icons-material";
import { styled } from "@mui/material";

import { ButtonTitleConteiner, HeaderConteiner } from "./styles";

export default function Header() {
  return (
    <HeaderConteiner>
      <ButtonTitleConteiner>
        <h1>Gerenciador de Veículos</h1>
      </ButtonTitleConteiner>
    </HeaderConteiner>
  );
}
