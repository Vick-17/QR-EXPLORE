import {styled} from "@mui/material/styles";
import Button from "@mui/material/Button";
import CloudUploadIcon from '@mui/icons-material/CloudUpload';
import Box from "@mui/material/Box";
import {Typography} from "@mui/material";
import {useEffect, useState} from "react";

const VisuallyHiddenInput = styled('input') ({
  clip: 'rect(0 0 0 0)',
  clipPath: 'inset(50%)',
  height: 1,
  overflow: 'hidden',
  position: 'absolute',
  bottom: 0,
  left: 0,
  whiteSpace: 'nowrap',
  width: 1,
});

function InputPlaceImageUpload() {
  const [selectedFile, setSelectedFile] = useState();
  const [preview, setPreview] = useState();

  // Crée un aperçu en tant qu'effet secondaire, à chaque fois que le fichier sélectionné est changé
  useEffect(() => {
    if (!selectedFile) {
      setPreview(undefined)
      return;
    }

    const objectUrl = URL.createObjectURL(selectedFile);
    setPreview(objectUrl);

    // Libère la mémoire à chaque fois que le composant est démonté
    return () => URL.revokeObjectURL(objectUrl);
  }, [selectedFile]);
  
  const onSelectFile = (e) => {
    if (!e.target.files || e.target.files.length === 0) {
      setSelectedFile(undefined);
      return;
    }
    
    setSelectedFile(e.target.files[0])
  }

  return (
    <Box>
      <Button
        sx={{ mt: 3, mb: 1 }}
        component="label"
        role={undefined}
        variant="contained"
        tabIndex={-1}
        startIcon={<CloudUploadIcon />}
      >
        Importer une photo
        <VisuallyHiddenInput
          type="file"
          onChange={onSelectFile} />
      </Button>

      <Typography sx={{ mt: 5, mb: 1 , display:'flex'}}>
        <strong>Aperçu de la photo importée :</strong>
      </Typography>

      {selectedFile && (
        <img
          src={preview}
          alt={"Photo de l'image importée"}
          width="auto"
          height="300px"/>
      )}

    </Box>


  );
}

export default InputPlaceImageUpload;