// import React from "react";
import { Box, Button, Typography } from "@mui/material";
import { Link } from "react-router-dom";
import "../Style/pages/HomePage.css";

function Welcome() {
  return (
    <Box className="main-container">
      <Box className="content-box">
        <Typography variant="h1" className="title">
          QR Explore
        </Typography>
        <Typography variant="body1" className="description">
          Voici un petit texte qui décrit quelque chose d&apos;intéressant.
        </Typography>
        <Button variant="contained" className="button">
          <Link to='/about' className="homepage-items">Explorer maintenant</Link>
        </Button>
      </Box>

    </Box>
  );
}

export default Welcome;
