export const getDocumentos = async () => {
    try {
      const response = await Api.get("/documentos");
  
      return response.data;
    } catch (error) {
      handlerApiError(error, "getDocumentos");
    }
  };