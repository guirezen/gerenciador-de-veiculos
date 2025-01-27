import { Close, SearchOutlined } from "@mui/icons-material";
import { styled } from "@mui/material";

import { ButtonSearch, ConteinerTitleInput, InputsContainer, SearchConteiner, TitleIconContainer } from "./styles";

const TituloBuscadorListagem = ({ titulo, setFilters }) => {
  return (
    <ConteinerTitleInput>
      <TitleIconContainer>
        <h1>{titulo}</h1>
      </TitleIconContainer>
      <InputsContainer>
        <SearchConteiner>
          <input
            type="text"
            placeholder="Digite o modelo, fabricante, tipo ou o ano do veículo..."
            onChange={(event) => setFilters(event.target.value)}
          />
        </SearchConteiner>
      </InputsContainer>
    </ConteinerTitleInput>
  );
};

export default TituloBuscadorListagem;
