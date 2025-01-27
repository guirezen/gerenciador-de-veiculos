import { Alert } from "@mui/material";

import SkeletonListagem from "../SkeletonListagem";
import TabelaListagem from "../TabelaListagem";

const ListagemConteudo = ({
  isFetching,
  isError,
  errorMessage,
  heightSkeleton,
  spacingSkeleton,
  numberSkeleton,
  textoAlerta,
  listaConteudo,
  columnsTabela,
  onDelete,
  handleEdit,
  handleClickOpenDialog
}) => {
  const listSkeleton = Array.from({ length: numberSkeleton });

  return (
    <div>
      {isFetching ? (
        <SkeletonListagem
          heightSkeleton={heightSkeleton}
          spacingSkeleton={spacingSkeleton}
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
            onEdit={handleEdit}
            handleClickOpenDialog={handleClickOpenDialog}
        />
      )}
    </div>
  );
};

export default ListagemConteudo;
