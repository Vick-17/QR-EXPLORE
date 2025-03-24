import {useState} from "react";
import {post} from "../service/BackendService.js";

export const DetailsPlaceForm = ({submitted, onSubmit}) => {

  const [formData, setFormData] = useState({
    name: "",
    description: "",
    location: "",
    // placeType: null
  });

  const [loading, setLoading] = useState(false);

  const handleChange = (e) => {
    setFormData({...formData, [e.target.name]: e.target.value});
  }

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    try {
      await post("/places", formData);
      console.log(formData);
      onSubmit(true);
    } catch (e) {
      console.error("Erreur lors de l'ajout du lieu", e);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div>
      <h2>Détails du lieu à renseigner :</h2>
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label className="form-label">Nom du lieu :</label>
          <input type="text" className="form-control" name="name" value={formData.name} onChange={handleChange} required />
        </div>
        <div className="mb-3">
          <label className="form-label">Description :</label>
          <textarea className="form-control" name="description" value={formData.description} onChange={handleChange} required />
        </div>
        <div className="mb-3">
          <label className="form-label">Localisation :</label>
          <input type="text" className="form-control" name="location" value={formData.location} onChange={handleChange} required />
        </div>

        <button type="submit" className="btn btn-success">
          {loading ? "Chargement..." : "Valider les infos"}
        </button>
      </form>
    </div>

  );

}
