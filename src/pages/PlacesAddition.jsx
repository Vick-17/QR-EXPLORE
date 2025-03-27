import * as React from 'react';
import Box from '@mui/material/Box';
import Stepper from '@mui/material/Stepper';
import Step from '@mui/material/Step';
import StepLabel from '@mui/material/StepLabel';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import {useState} from "react";
import {DetailsPlaceForm} from "../components/DetailsPlaceForm.jsx";
import InputPlaceImageUpload from "../components/InputPlaceImageUpload.jsx";

const steps = ["Renseignement des infos sur le lieu", "Ajout d'une photo du lieu", "Création d'un QR code associé au lieu"];

function PlacesAddition() {
  const [activeStep, setActiveStep] = useState(0);
  const [submitted, setSubmitted] = useState(false);
  const [placeId, setPlaceId] = useState(0);
  const [pictureAdded, setPictureAdded] = useState(false);

  const handleNext = () => {
    setActiveStep((prevActiveStep) => prevActiveStep + 1);
    console.log(placeId);
  };


  const handleReset = () => {
    setActiveStep(0);
    setSubmitted(false);
    setPictureAdded(false);
  };

  return (
    <Box sx={{ width: '90%', mt: 2, mx: 'auto'}}>
      <Stepper activeStep={activeStep}>
        {steps.map((label) => {
          const stepProps = {};
          const labelProps = {};

          return (
            <Step key={label} {...stepProps}>
              <StepLabel {...labelProps}><strong>{label}</strong></StepLabel>
            </Step>
          );
        })}
      </Stepper>
      {activeStep === steps.length ? (
        <React.Fragment>
          <Typography sx={{ mt: 5 }}>
            <strong>Toutes les étapes de l'ajout d'un lieu avec QR code ont été complétées !</strong>
          </Typography>
          <Box sx={{ display: 'flex', flexDirection: 'row', pt: 2 }}>
            <Box sx={{ flex: '1 1 auto' }} />
            <Button variant="contained" onClick={handleReset}>Ajout d'un nouveau lieu</Button>
          </Box>
        </React.Fragment>
      ) : (
        <React.Fragment>
          {/* Contenu de chaque étape */}

          <Typography sx={{ mt: 2, mb: 1, textAlign: 'center' }}>
            <strong>Étape n°{activeStep + 1} : {steps[activeStep]}</strong>
          </Typography>

          {/* Pour l'étape n°1 : formulaire pour renseigner les infos sur le lieu à ajouter */}
          {activeStep === 0 && (
            <DetailsPlaceForm submitted={submitted} onSubmit={setSubmitted} setPlaceId={setPlaceId}/>
          )}

          {/* Pour l'étape n°2 : ajout d'une photo du lieu */}
          {activeStep === 1 && (
            <InputPlaceImageUpload pictureAdded={pictureAdded} setPictureAdded={setPictureAdded} placeId={placeId} />
          )}

          {/* Pour l'étape n°3 : génération d'un QR code associé au lieu */}
          {activeStep === steps.length - 1 && (
            <Box>
              Composant de génération de QR code
            </Box>
          )}

          {/* Boutons PRÉCÉDENT et SUIVANT ou TERMINER */}
          <Box sx={{ display: 'flex', flexDirection: 'row', pt: 2 }}>

            {/* À l'étape 1 et si le formulaire a été soumis, le bouton SUIVANT apparait */}
            {(activeStep === 0 && submitted) && (
              <Button
                variant="contained"
                onClick={handleNext}>
                Suivant
              </Button>
            )}

            {/* À l'étape 2 et si l'image a été associée au lieu, le bouton SUIVANT apparait */}
            {(activeStep === 1 && pictureAdded) && (
              <Button
                variant="contained"
                onClick={handleNext}>
                Suivant
              </Button>
            )}

            {activeStep === 2 && (
              <Button
                variant="contained"
                onClick={handleNext}>
                {activeStep === steps.length - 1 ? 'Terminer' : 'Suivant'}
              </Button>
            )}


          </Box>
        </React.Fragment>
      )}
    </Box>
  );
}

export default PlacesAddition;
