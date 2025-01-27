import { Dialog, DialogContent, DialogTitle, styled } from "@mui/material";

export const DialogNewForm = ({ titulo, open, handleClick, children, maxWidth }) => {
  return (
    <Dialog open={open} onClose={handleClick} fullWidth maxWidth={maxWidth ? maxWidth : "md"}>
      <DialogTitle>{titulo}</DialogTitle>
      <DialogContent>{children}</DialogContent>
    </Dialog>
  );
};

export default DialogNewForm;
