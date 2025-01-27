import styled from "styled-components";

export const CustomDrawer = styled.div`
  width: 338px;
  min-height: 100vh;
  background-color: #f0f1f3;
  transform: translateX(0);
  transition: transform 0.3s ease, width 0.3s ease;

  &.hidden {
    transform: translateX(-338px);
    width: 0;
    overflow: hidden;
  }

  @media (max-width: 850px) {
    width: 220px;
    position: absolute;
    z-index: 2;
  }
`;
