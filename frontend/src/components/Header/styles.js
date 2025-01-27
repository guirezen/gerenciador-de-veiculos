import styled from "styled-components";

export const HeaderConteiner = styled.header`
  display: flex;
  width: 100%;
  height: 80px;
  justify-content: space-between;
  align-items: center;
  background-color: #f0f1f3;
  padding: 0 10px;
`;

export const ButtonTitleConteiner = styled.div`
  display: inherit;
  align-items: center;
  color: #818181;
  padding-right: 5px;

  h1 {
    font-size: clamp(14px, 3vw, 40px);;
  }
`;
