import { Dialog, DialogContent, DialogTitle } from "@mui/material";

export const DialogGeneric = ({
  titulo,
  open,
  handleClickOpen,
  children,
  setVeiculoSelected,
}) => {
  return (
    <Dialog
      open={open}
      onClose={() => {
        handleClickOpen();
        setVeiculoSelected(null);
      }}
      fullWidth
      maxWidth={"sm"}
    >
      <DialogTitle>{titulo}</DialogTitle>
      <DialogContent>{children}</DialogContent>
    </Dialog>
  );
};

export default DialogGeneric;
