import styled from "styled-components";

export const ConteinerTitleInput = styled.section`
    height: 100px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    border-bottom: 1px solid #D5D5D5;
    width: 100%;

    h1 {
        font-size: ${({ fontsize }) => fontsize ? fontsize : '32px'};
        color: #818181;
        font-weight: 500;
    }

    @media (max-width: 900px) {
        flex-direction: column;
        align-items: flex-start;
        height: auto;
    }
`

export const TitleIconContainer = styled.div`
    display: flex;
    align-items: center;
    margin-bottom: 15px;

    @media (max-width: 900px) {
        margin-top: 10px;
    }
`

export const InputsContainer = styled.div`
    height: 121px;
    width: 100%;
    max-width: 684px;
    display: inherit;
    justify-content: space-between; 
    align-items: center;

    @media (max-width: 900px) {
        height: 90px;
        justify-content: flex-start;
    }
`

export const SearchConteiner = styled.div`
    position: relative;
    width: 100%;
    max-width: 409px;
    margin: 0 10px 0 10px;

    input {
        border: 1px solid #C3C3C3;
        background: #FFFFFF;
        height: 55px;
        border-radius: 28px;
        padding: 20px 32px;
        padding-right: 60px;
        width: 100%;
    } 
    
    @media (max-width: 900px) {
        margin-left: 0;
    }
`

export const ButtonSearch = styled.button`
    position: absolute;
    top: 13px;
    right: 18px;
    background-color: transparent;
    border: none;
`