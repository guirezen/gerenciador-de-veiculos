import { useState } from "react";

import DialogNewForm from "../../components/DialogNewForm";
import ListagemConteudo from "../../components/ListagemConteudo";
import TituloBuscadorListagem from "../../components/TituloBuscadorListagem";
import { ButtonNew, ContainerTituloButtonForm, MainContainer } from "./styles";

const ListagemVeiculos = () => {
  const [openDialog, setOpenDialog] = useState(false);

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

      {/* <ListagemConteudo
        listaConteudo={data}
        isFetching={isFetching}
        heightSkeleton={"60px"}
        spacingSkeleton={8}
        numberSkeleton={8}
        isError={isError}
        textoAlerta={"Ocorreu um erro ao carregar os veículos:"}
        errorMessage={error?.message}
        columnsTabela={columns}
        onDelete={onDelete}
        handleEdit={openEditDocumento}
      /> */}
    </MainContainer>
  );
};

export default ListagemVeiculos;
