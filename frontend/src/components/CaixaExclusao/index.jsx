import { Box, CircularProgress } from "@mui/material";
import Button from "@mui/material/Button";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import DialogContentText from "@mui/material/DialogContentText";
import DialogTitle from "@mui/material/DialogTitle";

import BackdropComponent from "../Loading";

export function CaixaExclusao({
  titulo,
  corpo,
  funcaoCancelar,
  funcaoExcluir,
  isPending,
}) {
  return (
    <div>
      <Dialog
        open={true}
        onClose={funcaoCancelar}
        aria-labelledby="alert-dialog-title"
        aria-describedby="alert-dialog-description"
      >
        {isPending && <BackdropComponent />}
        <DialogTitle id="alert-dialog-title">{titulo}</DialogTitle>
        <DialogContent>
          <DialogContentText id="alert-dialog-description">
            {corpo}
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button onClick={funcaoCancelar}>Cancelar</Button>
          <Button onClick={funcaoExcluir}>Excluir</Button>
        </DialogActions>
      </Dialog>
    </div>
  );
}
