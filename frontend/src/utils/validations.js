export const validarCombustível = (value, typeSelected) => {
  if (typeSelected === "carro") {
    const combustiveisValidos = ["gasolina", "álcool", "flex"];

    const normalize = (str) =>
      str
        .toLowerCase()
        .normalize("NFD")
        .replace(/[\u0300-\u036f]/g, "");

    const valorNormalizado = normalize(value);

    const isValid = combustiveisValidos
      .map(normalize)
      .includes(valorNormalizado);

    return isValid || "Tipo de combustível inválido";
  }

  return true;
};
