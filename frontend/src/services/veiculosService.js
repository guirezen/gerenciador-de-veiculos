import { handlerApiError } from './../utils/errorHandler';
import Api from "./api";

export const getVeiculos = async () => {
    try {
      const response = await Api.get();
  
      return response.data;
    } catch (error) {
      handlerApiError(error, "getVeiculos");
    }
  };