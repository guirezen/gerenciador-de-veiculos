import { Button, MenuItem, TextField } from "@mui/material";
import { useMutation, useQueryClient } from "@tanstack/react-query";
import { useEffect, useState } from "react";
import { Controller, useForm } from "react-hook-form";

import { postVeiculo, putVeiculo } from "../../services/veiculosService";
import { ContainerButtons, ContainerForm } from "./styles";

const FormVeiculo = ({
  handleClick,
  veiculoSelected = {
    defaultValues: {
      modelo: "",
      fabricante: "",
      ano: 2000,
      preco: 0,
      tipo: "",
      quantidadePortas: 1,
      tipoCombustivel: "",
      cilindrada: 0,
    },
  },
}) => {
  const [typeSelected, setTypeSelected] = useState("");
  const { control, handleSubmit, reset } = useForm();

  const queryClient = useQueryClient();

  const onSubmit = (data) => {
    if (veiculoSelected.id) {
      const newData = { ...data, id: veiculoSelected.id };
      putVeiculoMutation.mutate(newData);
    } else {
      postVeiculoMutation.mutate(data);
    }
  };

  const postVeiculoMutation = useMutation({
    mutationFn: (data) => postVeiculo(data),
    onSuccess: (response) => {
      console.log("Veiculo cadastrado com sucesso!", response);

      queryClient.invalidateQueries(["veiculos"]);
      queryClient.fetchQuery(["veiculos"]);
    },
    onError: (error) => console.error("Erro ao cadastrar o veículo", error),
  });

  const putVeiculoMutation = useMutation({
    mutationFn: (data) => putVeiculo(data),
    onSuccess: (response) => {
      console.log("Veículo atualizado com sucesso!");

      queryClient.invalidateQueries(["veículos"]);
      queryClient.fetchQuery(["veículos"]);
    },
    onError: (error) => console.error("Erro ao editar o veículo: ", error),
  });

  useEffect(() => {
    if (veiculoSelected) {

      if (veiculoSelected.tipo !== typeSelected) {
        setTypeSelected(veiculoSelected.tipo);
      }

      reset({
        modelo: veiculoSelected.modelo,
        fabricante: veiculoSelected.fabricante,
        ano: veiculoSelected.ano,
        preco: veiculoSelected.preco,
        tipo: veiculoSelected.tipo,
        quantidadePortas: veiculoSelected.quantidadePortas,
        tipoCombustivel: veiculoSelected.tipoCombustivel,
        cilindrada: veiculoSelected.cilindrada,
      });
    }
  }, [veiculoSelected]);

  return (
    <>
      <ContainerForm id="form-veiculo" onSubmit={handleSubmit(onSubmit)}>
        <Controller
          name="modelo"
          control={control}
          defaultValue=""
          rules={{ required: "O modelo é obrigatório" }}
          render={({ field, fieldState: { error } }) => (
            <TextField
              id="outlined-basic"
              label="Modelo"
              {...field}
              variant="outlined"
              fullWidth
              error={!!error}
              helperText={error ? error.message : null}
            />
          )}
        />
        <Controller
          name="fabricante"
          control={control}
          defaultValue=""
          rules={{ required: "O fabricante é obrigatório" }}
          render={({ field, fieldState: { error } }) => (
            <TextField
              id="outlined-basic"
              label="Fabricante"
              {...field}
              variant="outlined"
              fullWidth
              error={!!error}
              helperText={error ? error.message : null}
            />
          )}
        />
        <Controller
          name="ano"
          control={control}
          defaultValue={2000}
          rules={{ required: "O ano é obrigatório" }}
          render={({ field, fieldState: { error } }) => (
            <TextField
              id="outlined-basic"
              label="Ano"
              {...field}
              type="number"
              variant="outlined"
              fullWidth
              inputProps={{
                min: 1886,
                step: 1,
              }}
              error={!!error}
              helperText={error ? error.message : null}
            />
          )}
        />
        <Controller
          name="preco"
          control={control}
          defaultValue={0}
          rules={{ required: "O preço é obrigatório" }}
          render={({ field, fieldState: { error } }) => (
            <TextField
              id="outlined-basic"
              label="Preço"
              {...field}
              type="number"
              variant="outlined"
              fullWidth
              error={!!error}
              helperText={error ? error.message : null}
            />
          )}
        />
        <Controller
          name="tipo"
          control={control}
          defaultValue=""
          render={({ field, fieldState: { error } }) => (
            <TextField
              id="outlined-basic"
              variant="outlined"
              {...field}
              select
              label="Tipo"
              fullWidth
              error={!!error}
              helperText={error ? error.message : null}
              onChange={(event) => {
                field.onChange(event);
                setTypeSelected(event.target.value);
              }}
            >
              <MenuItem value="" disabled>
                {" "}
                Selecione um Tipo
              </MenuItem>
              <MenuItem value="carro">Carro</MenuItem>
              <MenuItem value="moto">Moto</MenuItem>
            </TextField>
          )}
        />
        {typeSelected === "carro" ? (
          <>
            <Controller
              name="quantidadePortas"
              control={control}
              defaultValue={1}
              rules={{
                required:
                  typeSelected === "carro"
                    ? "Informar a quantidade de portas é obrigatório"
                    : false,
              }}
              render={({ field, fieldState: { error } }) => (
                <TextField
                  id="outlined-basic"
                  label="Quantidade de Portas"
                  {...field}
                  type="number"
                  variant="outlined"
                  fullWidth
                  inputProps={{
                    min: 1,
                    max: 6,
                    step: 1,
                  }}
                  error={!!error}
                  helperText={error ? error.message : null}
                />
              )}
            />
            <Controller
              name="tipoCombustivel"
              control={control}
              defaultValue=""
              rules={{
                required:
                  typeSelected === "carro"
                    ? "O tipo de combustivel é obrigatório"
                    : false,
              }}
              render={({ field, fieldState: { error } }) => (
                <TextField
                  id="outlined-basic"
                  label="Tipo de Combustível"
                  {...field}
                  variant="outlined"
                  fullWidth
                  error={!!error}
                  helperText={error ? error.message : null}
                />
              )}
            />
          </>
        ) : typeSelected === "moto" ? (
          <Controller
            name="cilindrada"
            control={control}
            defaultValue={0}
            rules={{
              required:
                typeSelected === "moto"
                  ? "As cilindradas são obrigatórias"
                  : false,
            }}
            render={({ field, fieldState: { error } }) => (
              <TextField
                id="outlined-basic"
                label="Cilindrada"
                {...field}
                type="number"
                variant="outlined"
                fullWidth
                error={!!error}
                helperText={error ? error.message : null}
              />
            )}
          />
        ) : null}
      </ContainerForm>
      <ContainerButtons>
        <Button onClick={handleClick}>Cancelar</Button>
        <Button form={"form-veiculo"} type="submit">
          Enviar
        </Button>
      </ContainerButtons>
    </>
  );
};

export default FormVeiculo;
