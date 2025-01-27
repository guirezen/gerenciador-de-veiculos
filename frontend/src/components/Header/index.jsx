import { Menu } from "@mui/icons-material";
import { styled } from "@mui/material";

import { ButtonTitleConteiner, HeaderConteiner } from "./styles";

const MenuCustom = styled(Menu)(() => ({
    fontSize: '2rem',
    marginRight: '30px',
}))

export default function Header({ handleDrawerToggle }) {

    return (
        <HeaderConteiner>
            <ButtonTitleConteiner>
                <MenuCustom  
                    onClick={handleDrawerToggle}
                />
                <h1>Administrativo Edifs</h1>
            </ButtonTitleConteiner>
        </HeaderConteiner>
    )
}