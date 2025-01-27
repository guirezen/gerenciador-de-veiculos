import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { useEffect, useState } from "react";

import DialogNewForm from "../../components/DialogNewForm";
import ListagemConteudo from "../../components/ListagemConteudo";
import TituloBuscadorListagem from "../../components/TituloBuscadorListagem";
import { deleteVeiculo, getVeiculoByFiltro, getVeiculos } from "../../services/veiculosService";
import FormVeiculo from "../FormVeiculo";
import { ButtonNew, ContainerTituloButtonForm, MainContainer } from "./styles";

const ListagemVeiculos = () => {
  const [openDialog, setOpenDialog] = useState(false);
  const [veiculoSelected, setVeiculoSelected] = useState();
  const [filters, setFilters] = useState("");

  const queryClient = useQueryClient();

  const { data, isFetching, isError, error } = useQuery({
    queryKey: ["veiculos", filters],
    queryFn: () => (filters ? getVeiculoByFiltro(filters) : getVeiculos()),
  });

  const deleteVeiculoMutation = useMutation({
    mutationFn: (id) => deleteVeiculo(id),
    onSuccess: () => {
      console.log("Veículo deleteado com sucesso!");
      queryClient.invalidateQueries(["veiculos"]);
    },
    onError: (error) => {
      console.error("Erro ao deletar veículo: ", error);
    },
  });

  const columns = [
    { label: "Modelo", accessor: "modelo" },
    { label: "Ano", accessor: "ano" },
    { label: "Preço", accessor: "preco" },
  ];

  const handleClickOpenDialog = () => {
    setOpenDialog(!openDialog);
  };

  const handleDeleteVeiculo = (id) => {
    deleteVeiculoMutation.mutate(id);
  };

  const onDelete = {
    handleDelete: handleDeleteVeiculo,
    isPending: deleteVeiculoMutation.isPending,
    titulo: "Deseja Excluir o Veiculo?",
    descricao:
      "Ao excluir o veículo, todas as informações relacionadas ou vinculadas serão excluídas. Caso queira apagar, basta selecionar a opção excluir.",
  };

  const openEditVeiculo = (veiculo) => {
    setVeiculoSelected(veiculo);
    handleClickOpenDialog();
  };

  useEffect(() => {
    setVeiculoSelected("");
  }, [openDialog]);

  return (
    <MainContainer>
      <ContainerTituloButtonForm>
        <TituloBuscadorListagem
          titulo={"Veículos"}
          setFilters={setFilters}
        />
      </ContainerTituloButtonForm>

      <DialogNewForm
        titulo={"Novo Veículo"}
        handleClick={handleClickOpenDialog}
        open={openDialog}
        maxWidth={"sm"}
      >
        <FormVeiculo
          handleClick={handleClickOpenDialog}
          veiculoSelected={veiculoSelected}
        />
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
        onDelete={onDelete}
        handleEdit={openEditVeiculo}
        handleClickOpenDialog={handleClickOpenDialog}
      />
    </MainContainer>
  );
};

export default ListagemVeiculos;
