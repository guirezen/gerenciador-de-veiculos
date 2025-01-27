import { EditNote, SearchOutlined } from "@mui/icons-material";
import { MenuItem, Select, styled } from "@mui/material";
import { useState } from "react";

import { ButtonSearch, ConteinerTitleInput, InputsContainer, SearchConteiner, TitleIconContainer } from "./styles";

const CustomSelect = styled(Select)(() => ({
  width: "50%",
  maxWidth: "254px",
  border: "1px solid #C3C3C3",
  background: "#FFFFFF",
  color: "#AFAFAF",
  height: "55px",
  borderRadius: "28px",
  padding: "20px 32px 20px 15px ",
}));

const SearchIconCustom = styled(SearchOutlined)(() => ({
  fontSize: "30px",
  color: "#BBBBBB",
}));

const TituloBuscadorListagem = ({
  titulo,
  searchs,
  icon,
  fontsize,
  onFilterChange,
}) => {
  const [buscaPublicacao, setBuscaPublicacao] = useState('');
  const [selectPublicacao, setSelectPublicacao] = useState('TODOS');

  return (
    <ConteinerTitleInput fontsize={fontsize}>
      <TitleIconContainer>
        <h1>{titulo}</h1>
        {icon && (
          <EditNote
            sx={{ fontSize: "47px", marginLeft: "30px", color: "#818181" }}
          />
        )}
      </TitleIconContainer>
    </ConteinerTitleInput>
  );
};

export default TituloBuscadorListagem;
