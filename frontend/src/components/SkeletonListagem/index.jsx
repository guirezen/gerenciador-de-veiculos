import { Grid, Skeleton } from "@mui/material";

const SkeletonListagem = ({ listSkeleton }) => {
    
  return (
    <Grid container spacing={8}>
      <Grid item xs>
        {listSkeleton.map((_, index) => (
          <div key={index}>
            <Skeleton sx={{ height: "60px" }} />
          </div>
        ))}
      </Grid>
    </Grid>
  );
};

export default SkeletonListagem;
