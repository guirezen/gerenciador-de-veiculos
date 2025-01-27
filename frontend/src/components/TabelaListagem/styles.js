import styled from "styled-components";

export const ContainerSection = styled.section`
  width: 100%;
  padding-bottom: 25px;
  overflow-x: auto;
  overflow-y: hidden;
`;

export const TableContainer = styled.table`
  width: 100%;
  border-collapse: collapse;

  min-width: 500px;
`;

export const HeadTable = styled.thead`
  text-align: left;

  tr {
    height: 50px;
    color: #414141;
    background-color: #f5f5f5;
  }
`;

export const THead = styled.th`
  font-size: 17px;

  &:nth-child(1) {
    width: 40%;
    padding-left: 50px;
  }
  &:nth-child(2) {
    width: 32%;
    padding-left: 15px;
  }
`;

export const RowTable = styled.tr`
  height: 50px;
  color: #414141;
  background-color: #f5f5f5;

  &:nth-child(odd) {
    background-color: #fff;
    border-top: 2px solid #dddddd;
    border-bottom: 2px solid #dddddd;
  }

  td:nth-child(1) {
    padding-left: 50px;
  }

  td:nth-child(2),
  td:nth-child(3) {
    padding-left: 15px;
  }

  @media (max-width: 600px) {
    height: auto;

    td:nth-child(1) {
      padding-left: 15px;
    }

    td {
      font-size: 13px;
      padding-top: 10px;
      padding-bottom: 10px;
    }
  }
`;

export const TDButtons = styled.td`
  height: 50px;
  vertical-align: middle;
  text-align: right;

  button {
    background-color: transparent;
    border: none;
    width: 50px;
    height: 40px;
  }

  .MuiSvgIcon-root {
    color: #c3c3c3;
  }

  @media (max-width: 1003px) {
    button:nth-child(1) {
      margin-right: 0px;
    }
  }
`;

export const PaginationButtonContainer = styled.div`
  width: 100%;
  margin-top: 65px;
  display: flex;
  justify-content: center;
  align-items: center;
  position: relative;

  a {
    position: absolute;
    right: 0;
  }

  @media (max-width: 650px) {
    justify-content: flex-start;
  }
`;

export const ButtonNew = styled.button`
  display: flex;
  align-items: center;
  justify-content: center;
  position: absolute;
  right: 0;
  bottom: 0;
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
