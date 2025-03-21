/* eslint-disable no-unused-vars */
import { useState } from "react";
import { post } from "../service/BackendService";

import "../Style/pages/SignUp.css"

const SignUp = () => {
  const [formData, setFormData] = useState({
    firstName: "",
    lastName: "",
    username: "",
    email: "",
    password: "",
  });
  const [loading, setLoading] = useState(false);

  // const [passwordError, setPasswordError] = useState("");

  // const validatePassword = (password) => {
  //   const regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?])[\w\d@$!%*?&]{12,}$/;
  //   return regex.test(password);
  // };

  const handleChange = (e) => {
    setFormData({...formData, [e.target.name]: e.target.value});
  }


  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    try {
      await post("/users/register", formData);
      console.log(formData)
      // window.location.href = "/auth";
    } catch (e) {
      console.error("Erreur lors de l'inscription", e);
    } finally {
      setLoading(false);
    }
    
    
    
  };

  return (
    <div className="signup-card container d-flex justify-content-center align-items-center vh-80">
      <div className="card shadow-lg">
        <h2 className="text-center mb-4">Inscription</h2>
        <form onSubmit={handleSubmit}>
          <div className="mb-3">
            <label className="form-label">Nom de fammille</label>
            <input type="text" className="form-control" name="firstName" value={formData.firstName} onChange={handleChange} required />
          </div>
          <div className="mb-3">
            <label className="form-label">Prénom</label>
            <input type="text" className="form-control" name="lastName" value={formData.lastName} onChange={handleChange} required />
          </div>
          <div className="mb-3">
            <label className="form-label">Pseudo</label>
            <input type="text" className="form-control" name="username" value={formData.username} onChange={handleChange} required />
          </div>
          <div className="mb-3">
            <label className="form-label">Email</label>
            <input type="email" className="form-control" name="email" value={formData.email} onChange={handleChange} required />
          </div>
          <div className="mb-3">
            <label className="form-label">Mot de passe</label>
            <input type="password" className="form-control" name="password" value={formData.password} onChange={handleChange} required />
            {/* {passwordError && <div className="text-danger">{passwordError}</div>} */}
          </div>
          <button type="submit" className="btn btn-success signup-btn">
            {loading ? "Chargement..." : "S'inscrire"}
          </button>
        </form>
      </div>
    </div>
  );
};

export default SignUp;
