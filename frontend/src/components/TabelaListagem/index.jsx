import { DeleteOutlined, DetailsOutlined, EditOutlined, MoreOutlined } from "@mui/icons-material";
import { Pagination, styled } from "@mui/material";
import { useState } from "react";

import { CaixaExclusao } from "../CaixaExclusao";
import DialogGeneric from "../DialogGeneric";
import { ButtonNew, ContainerSection, HeadTable, PaginationButtonContainer, RowTable, TableContainer, TDButtons, THead } from "./styles";

const CustomPagination = styled(Pagination)(() => ({
  ".MuiButtonBase-root": {
    color: "#A0A0A0",
    fontSize: "19px",
    fontWeight: "bold",
  },
  ".Mui-selected": {
    backgroundColor: "#A0A0A0!important",
    color: "#fff",
  },
}));

const TabelaListagem = ({
  columns,
  listaConteudo,
  onDelete,
  onEdit,
  onDetails,
  handleClickOpenEdit,
}) => {
  const [currentPage, setCurrentPage] = useState(1);
  const [abrirCaixaExclusao, setAbrirCaixaExclusao] = useState(false);
  const [idContent, setIdContent] = useState();

  const toggleCaixaExclusao = (id) => {
    setAbrirCaixaExclusao(!abrirCaixaExclusao);
    setIdContent(id);
  };

  const veiculosPorPage = 10;
  const startIndex = (currentPage - 1) * veiculosPorPage;
  const endIndex = startIndex + veiculosPorPage;
  const displayedContent = listaConteudo.slice(startIndex, endIndex);

  return (
    <ContainerSection>
      <TableContainer>
        <HeadTable>
          <tr>
            {columns.map((column) => (
              <THead key={column.label}>{column.label}</THead>
            ))}
            <THead></THead>
          </tr>
        </HeadTable>
        <tbody>
          {displayedContent?.map((content) => (
            <RowTable key={content.id}>
              {columns.map((column) => (
                <td key={column.accessor} onClick={() => onDetails(content)}>
                  {column.render
                    ? column.render(content[column.accessor])
                    : content[column.accessor]}
                </td>
              ))}
              <TDButtons>
                <button type="button" onClick={() => onEdit(content)}>
                  <EditOutlined />
                </button>
                <button
                  type="button"
                  onClick={() => toggleCaixaExclusao(content.id)}
                >
                  <DeleteOutlined />
                </button>
              </TDButtons>
            </RowTable>
          ))}
        </tbody>
      </TableContainer>
      <PaginationButtonContainer>
        <CustomPagination
          count={Math.ceil(listaConteudo.length / veiculosPorPage)}
          shape="rounded"
          page={currentPage}
          onChange={(event, value) => setCurrentPage(value)}
          siblingCount={1}
          boundaryCount={0}
        />
        <ButtonNew onClick={handleClickOpenEdit}>+ Novo</ButtonNew>
      </PaginationButtonContainer>
      {abrirCaixaExclusao && (
        <CaixaExclusao
          titulo={onDelete.titulo}
          corpo={onDelete.descricao}
          funcaoCancelar={toggleCaixaExclusao}
          funcaoExcluir={() => onDelete.handleDelete(idContent)}
          isPending={onDelete.isPending}
        />
      )}
    </ContainerSection>
  );
};

export default TabelaListagem;
