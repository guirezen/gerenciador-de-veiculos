import { Dialog, DialogContent, DialogTitle } from "@mui/material";

export const DialogGeneric = ({
  titulo,
  open,
  handleClick,
  children,
  setVeiculoSelected
}) => {
  return (
    <Dialog
      open={open}
      onClose={() => {
        handleClick();
        setVeiculoSelected("");
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
