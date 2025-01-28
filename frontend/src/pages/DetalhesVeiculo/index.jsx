import Grid from "@mui/material/Grid";
import List from "@mui/material/List";
import ListItem from "@mui/material/ListItem";
import ListItemText from "@mui/material/ListItemText";

const DetalhesVeiculo = ({ veiculo }) => {
  const { id, ...veiculoSemId } = veiculo;

  return (
    <Grid item xs={12} md={6}>
      <List>
        {Object.entries(veiculoSemId).map(([key, value]) => {
          return (
            <ListItem key={key}>
              <ListItemText primary={key} secondary={value} />
            </ListItem>
          );
        })}
      </List>
    </Grid>
  );
};

export default DetalhesVeiculo;
