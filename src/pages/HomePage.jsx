import { Box, Button, Typography } from "@mui/material";
import "../Style/pages/HomePage.css";
import { Link } from 'react-router-dom';

function Welcome() {
  return (
    <Box className="main-container">
      <Box className="content-box">
        <Typography variant="h1" className="title">
          QR Explore
        </Typography>
        <Typography variant="body1" className="description">
          Voici un petit texte qui décrit quelque chose d intéressant.
        </Typography>
        <Link to="/places" className="link">
          <Button variant="contained" className="button">
            Explore Now
          </Button>
        </Link>
      </Box>

    </Box>
  );
}

export default Welcome;
