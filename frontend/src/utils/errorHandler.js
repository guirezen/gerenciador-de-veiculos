export const handlerApiError = (error, functionName) => {
  if (error.response) {
    console.error(
      `Erro na resposta da API em ${functionName}:`,
      error.response.data
    );
  } else if (error.request) {
    console.error(
      `Nenhuma resposta recebida em ${functionName}:`,
      error.request
    );
  } else {
    console.error(
      `Erro ao configurar a requisição em ${functionName}:`,
      error.message
    );
  }
  throw error;
};
