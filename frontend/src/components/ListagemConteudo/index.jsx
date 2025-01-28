import { Alert } from "@mui/material";

import SkeletonListagem from "../SkeletonListagem";
import TabelaListagem from "../TabelaListagem";

const ListagemConteudo = ({
  isFetching,
  isError,
  errorMessage,
  textoAlerta,
  listaConteudo,
  columnsTabela,
  onDelete,
  onEdit,
  onDetails,
  handleClickOpenEdit
}) => {
  const listSkeleton = [1, 2, 3];

  return (
    <div>
      {isFetching ? (
        <SkeletonListagem
          listSkeleton={listSkeleton}
        />
      ) : isError ? (
        <Alert severity="error">
          {textoAlerta} {errorMessage}
        </Alert>
      ) : (
        <TabelaListagem 
            listaConteudo={listaConteudo}       
            columns={columnsTabela}
            onDelete={onDelete}   
            onEdit={onEdit}
            onDetails={onDetails}
            handleClickOpenEdit={handleClickOpenEdit}
        />
      )}
    </div>
  );
};

export default ListagemConteudo;
