import { styled } from "@mui/material";
import Grid from "@mui/material/Grid";
import List from "@mui/material/List";
import ListItem from "@mui/material/ListItem";
import ListItemText from "@mui/material/ListItemText";
import { useEffect } from "react";

const Demo = styled("div")(({ theme }) => ({
  backgroundColor: theme.palette.background.paper,
}));

const DetalhesVeiculo = ({ veiculo }) => {
  const { id, ...veiculoSemId } = veiculo;

  return (
    <Grid item xs={12} md={6}>
      <Demo>
        <List>
          {Object.entries(veiculoSemId).map(([key, value]) => {
            return (
              <ListItem key={key}>
                <ListItemText primary={key} secondary={value} />
              </ListItem>
            );
          })}
        </List>
      </Demo>
    </Grid>
  );
};

export default DetalhesVeiculo;
