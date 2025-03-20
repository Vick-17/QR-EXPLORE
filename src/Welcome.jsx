// import React from "react";
import { Box, Button, Typography } from "@mui/material";
import "./Style/HomePage.css"; // Import du fichier CSS

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
        <Button variant="contained" color="primary" className="button">
          Explore Now
        </Button>
      </Box>

    </Box>
  );
}

export default Welcome;
