import { handlerApiError } from "./../utils/errorHandler";
import Api from "./api";

export const getVeiculos = async () => {
  try {
    const response = await Api.get();

    return response.data;
  } catch (error) {
    handlerApiError(error, "getVeiculos");
  }
};

export const deleteVeiculo = async (id) => {
  try {
    const response = await Api.delete(`/${id}`);

    return response.data;
  } catch (error) {
    handlerApiError(error, "deleteVeiculo");
  }
};

export const postVeiculo = async (data) => {
  try {
    const response = await Api.post("", data);

    return response.data;
  } catch (error) {
    handlerApiError(error, "postVeiculo");
  }
};

export const putVeiculo = async (data) => {
  try {
    const response = await Api.put(`/${data.id}`, data);

    return response.data;
  } catch (error) {
    handlerApiError(error, "putVeiculo");
  }
};

export const getVeiculoByFiltro = async (searchTerm) => {
  console.log("termo de busca: ", searchTerm);
  try {
    const response = await Api.get("/filtro", {
      params: { searchTerm },
    });
    return response.data;
  } catch (error) {
    handlerApiError(error, "getVeiculoByFiltro");
  }
};
