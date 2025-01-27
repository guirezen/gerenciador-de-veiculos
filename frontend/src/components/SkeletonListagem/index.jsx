import { Grid, Skeleton } from "@mui/material";

const SkeletonListagem = ({ listSkeleton, heightSkeleton, spacingSkeleton }) => {
    
  return (
    <Grid container spacing={spacingSkeleton}>
      <Grid item xs>
        {listSkeleton.map((_, index) => (
          <div key={index}>
            <Skeleton sx={{ height: heightSkeleton }} />
          </div>
        ))}
      </Grid>
    </Grid>
  );
};

export default SkeletonListagem;
