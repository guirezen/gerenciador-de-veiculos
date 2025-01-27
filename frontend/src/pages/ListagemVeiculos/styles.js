import styled from "styled-components";

export const MainContainer = styled.main`
  padding: 0px 51px 63px 71px;
  width: 100%;

  @media (max-width: 900px) {
    padding: 0px 20px 63px 20px;
  }
`;

export const ContainerButtons = styled.div`
  border-top: 1px solid #dadada;
  margin-top: 30px;
  padding-top: 10px;
`;

export const ContainerTituloButtonForm = styled.div`
  display: flex;
  position: relative;
  width: 100%;
`;

export const ButtonNew = styled.button`
  display: flex;
  align-items: center;
  justify-content: center;
  position: absolute;
  right: 0;
  top: 25%;
  background-color: #278ef5;
  border-radius: 10px;
  border: none;
  color: #fff;
  width: 90px;
  height: 40px;
  font-size: 16px;
  font-weight: 400;
  transition: transform 0.1s ease, background-color 0.1s ease;

  &:active {
    transform: scale(1.1);
    background-color: #1d6cc2;
  }
`;