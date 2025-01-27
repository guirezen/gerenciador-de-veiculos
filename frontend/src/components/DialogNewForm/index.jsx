import { Dialog, DialogContent, DialogTitle } from "@mui/material";

export const DialogNewForm = ({
  titulo,
  open,
  handleClick,
  children,
}) => {
  return (
    <Dialog
      open={open}
      onClose={() => {
        handleClick();
      }}
      fullWidth
      maxWidth={"sm"}
    >
      <DialogTitle>{titulo}</DialogTitle>
      <DialogContent>{children}</DialogContent>
    </Dialog>
  );
};

export default DialogNewForm;
