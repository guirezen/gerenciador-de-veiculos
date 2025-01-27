import { createGlobalStyle } from 'styled-components';

export const GlobalStyles = createGlobalStyle`
  * {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: 'Montserrat', sans-serif;
  }

  body {
    font-size: 16px;
    background-color: #ffffff;
    color: #000000;
    transition: all 0.3s linear;
    background-color: #fbfbfb;
  }

  button {
    cursor: pointer;
  }

  a {
    text-decoration: none;
  }
`;
