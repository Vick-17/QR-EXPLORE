import { useState } from "react";

import "../Style/pages/SignUp.css"
import { post } from "../service/BackendService";

const SignUp = () => {
  const endPoint = "/register";

  const [formData, setFormData] = useState({
    email: "",
    first_name: "",
    last_name: "",
    password: "",
    username: "",
  });

  const [passwordError, setPasswordError] = useState("");

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const validatePassword = (password) => {
    const regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[+-/\\@$!%*?&])[A-Za-z\d@$!%*?&]{12,}$/;
    return regex.test(password);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!validatePassword(formData.password)) {
      setPasswordError("Le mot de passe doit contenir au moins 12 caractères, une majuscule, une minuscule, un chiffre et un caractère spécial.");
      return;
    }

    // console.log("Token récupéré :", localStorage.getItem("userToken"));
    // console.log(formData);
    setPasswordError("");
    post(endPoint, formData);
    // console.log("Formulaire soumis avec succès :", formData);
  };

  return (
    <div className="signup-card container d-flex justify-content-center align-items-center vh-80">
      <div className="card shadow-lg">
        <h2 className="text-center mb-4">Inscription</h2>
        <form onSubmit={handleSubmit}>
          <div className="mb-3">
            <label className="form-label">First Name</label>
            <input type="text" className="form-control" name="first_name" value={formData.first_name} onChange={handleChange} required />
          </div>
          <div className="mb-3">
            <label className="form-label">Last Name</label>
            <input type="text" className="form-control" name="last_name" value={formData.last_name} onChange={handleChange} required />
          </div>
          <div className="mb-3">
            <label className="form-label">Username</label>
            <input type="text" className="form-control" name="username" value={formData.username} onChange={handleChange} required />
          </div>
          <div className="mb-3">
            <label className="form-label">Email</label>
            <input type="email" className="form-control" name="email" value={formData.email} onChange={handleChange} required />
          </div>
          <div className="mb-3">
            <label className="form-label">Password</label>
            <input type="password" className="form-control" name="password" value={formData.password} onChange={handleChange} required />
            {passwordError && <div className="text-danger">{passwordError}</div>}
          </div>
          <button type="submit" className="btn btn-success signup-btn">Sign Up</button>
        </form>
      </div>
    </div>
  );
};

export default SignUp;
