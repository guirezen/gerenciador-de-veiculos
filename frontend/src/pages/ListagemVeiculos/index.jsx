import { useQuery, useQueryClient } from "@tanstack/react-query";
import { useState } from "react";

import DialogNewForm from "../../components/DialogNewForm";
import ListagemConteudo from "../../components/ListagemConteudo";
import TituloBuscadorListagem from "../../components/TituloBuscadorListagem";
import { getVeiculos } from "../../services/veiculosService";
import { ButtonNew, ContainerTituloButtonForm, MainContainer } from "./styles";

const ListagemVeiculos = () => {
  const [openDialog, setOpenDialog] = useState(false);

  const queryClient = useQueryClient();

  const { data, isFetching, isError, error } = useQuery({
    queryKey: ["veiculos"],
    queryFn: getVeiculos,
  });

  const columns = [
    { label: "Modelo", accessor: "modelo" },
    { label: "Ano", accessor: "ano" },
    { label: "Preço", accessor: "preco" },
  ];

  const handleClick = () => {
    setOpenDialog(!openDialog);

    if (openDialog === true) {
    //   setDocSelected("");
    //   setFileSelected("");
    }
  };

  return (
    <MainContainer>
      <ContainerTituloButtonForm>
        <TituloBuscadorListagem titulo={"Veículos"} />
        <ButtonNew onClick={handleClick}>+ Novo</ButtonNew>
      </ContainerTituloButtonForm>
      
      <DialogNewForm
        titulo={"Novo Veículo"}
        handleClick={handleClick}
        open={openDialog}
        maxWidth={"sm"}
      >
        {/* <NovoDocumento handleClick={handleClick} docSelected={docSelected} /> */}
      </DialogNewForm>

      <ListagemConteudo
        listaConteudo={data}
        isFetching={isFetching}
        heightSkeleton={"60px"}
        spacingSkeleton={8}
        numberSkeleton={8}
        isError={isError}
        textoAlerta={"Ocorreu um erro ao carregar os veículos:"}
        errorMessage={error?.message}
        columnsTabela={columns}
        // onDelete={onDelete}
        // handleEdit={openEditDocumento}
      />
    </MainContainer>
  );
};

export default ListagemVeiculos;
