import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { useEffect, useState } from "react";

import DialogGeneric from "../../components/DialogGeneric";
import ListagemConteudo from "../../components/ListagemConteudo";
import TituloBuscadorListagem from "../../components/TituloBuscadorListagem";
import { deleteVeiculo, getVeiculoByFiltro, getVeiculos } from "../../services/veiculosService";
import DetalhesVeiculo from "../DetalhesVeiculo";
import FormVeiculo from "../FormVeiculo";
import { ContainerTitulo, MainContainer } from "./styles";

const ListagemVeiculos = () => {
  const [OpenEdit, setOpenEdit] = useState(false);
  const [veiculoSelected, setVeiculoSelected] = useState();
  const [filters, setFilters] = useState("");
  const [abrirDetalhesVeiculo, setAbrirDetalhesVeiculo] = useState(false);

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

  const handleClickOpenEdit = () => {
    setOpenEdit(!OpenEdit);
  };

  const handleClickOpenDetails = () => {
    setAbrirDetalhesVeiculo(!abrirDetalhesVeiculo);
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
    handleClickOpenEdit();
  };

  const openDetails = (veiculo) => {
    setVeiculoSelected(veiculo);
    handleClickOpenDetails();
  }

  return (
    <MainContainer>
      <ContainerTitulo>
        <TituloBuscadorListagem titulo={"Veículos"} setFilters={setFilters} />
      </ContainerTitulo>

      <DialogGeneric
        titulo={"Novo Veículo"}
        handleClick={handleClickOpenEdit}
        open={OpenEdit}
        setVeiculoSelected={setVeiculoSelected}
      >
        <FormVeiculo
          handleClick={handleClickOpenEdit}
          veiculoSelected={veiculoSelected}
          setVeiculoSelected={setVeiculoSelected}
        />
      </DialogGeneric>

      <DialogGeneric
        titulo={"Detalhes Veículo"}
        handleClick={openDetails}
        open={abrirDetalhesVeiculo}
        setVeiculoSelected={setVeiculoSelected}
      >
        <DetalhesVeiculo veiculo={veiculoSelected} />
      </DialogGeneric>

      <ListagemConteudo
        listaConteudo={data}
        isFetching={isFetching}
        isError={isError}
        textoAlerta={"Ocorreu um erro ao carregar os veículos:"}
        errorMessage={error?.message}
        columnsTabela={columns}
        onDelete={onDelete}
        onEdit={openEditVeiculo}
        onDetails={openDetails}
        handleClickOpenEdit={handleClickOpenEdit}
      />
    </MainContainer>
  );
};

export default ListagemVeiculos;
