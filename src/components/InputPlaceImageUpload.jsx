import {styled} from "@mui/material/styles";
import Button from "@mui/material/Button";
import CloudUploadIcon from '@mui/icons-material/CloudUpload';
import Box from "@mui/material/Box";
import {Typography} from "@mui/material";
import {useEffect, useState} from "react";
import {postFile} from "../service/BackendService.js";

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

function InputPlaceImageUpload({pictureAdded, setPictureAdded, placeId}) {
  const [selectedFile, setSelectedFile] = useState();
  const [preview, setPreview] = useState();
  const [loading, setLoading] = useState(false);
  const [buttonDisabled, setButtonDisabled] = useState(false);

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
    
    setSelectedFile(e.target.files[0]);
  };


  const handleClick = async (e) => {
    e.preventDefault();
    setLoading(true);

    const formData = new FormData();
    formData.append("picture", selectedFile);
    console.log(formData.get("picture"));

    try {
      const response = await postFile(`/places/${placeId}/picture`, formData);
      console.log(response);
      setPictureAdded(true);
      setButtonDisabled(true);
    } catch (e) {
      console.error("Erreur lors de l'ajout de la photo au lieu", e);
    } finally {
      setLoading(false);
    }
  };

  return (
    <Box>
      <h2>Photo du lieu à ajouter</h2>
      <Button sx={{ mt: 0, mb: 1, ml: 2 }}
              component="label"
              role={undefined}
              variant="contained"
              tabIndex={-1}
              startIcon={<CloudUploadIcon />}
              disabled={pictureAdded}>
        Importer une photo
        <VisuallyHiddenInput
          type="file"
          onChange={onSelectFile} />
      </Button>

      {selectedFile && (
        <Box>
          <Typography sx={{ mt: 5, mb: 4, display:'flex'}}>
            <strong>Aperçu de la photo importée :</strong>
          </Typography>
          <img
            src={preview}
            alt={"Photo de l'image importée"}
            width="auto"
            height="300px"/>

          <Button
            sx={{ mt: 2, mb: 1, ml: 6 }}
            variant="contained"
            color="success"
            onClick={handleClick}
            disabled={buttonDisabled}>
            {!pictureAdded ? (loading ? "Chargement..." : "Associer la photo au lieu") : "Photo ajoutée au lieu"}
          </Button>
        </Box>


      )}



    </Box>


  );
}

export default InputPlaceImageUpload;