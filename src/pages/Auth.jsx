import { useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import "../Style/pages/Auth.css"
import { login } from "../service/BackendService.js"
import { Link } from "react-router-dom";

function Auth() {
  const [isLogin, setIsLogin] = useState(true);
  const [formData, setFormData] = useState({
    username: "",
    password: "",
  })
  const [loading, setLoading] = useState(false);

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    })
  }

  const handleSubmit = async (e) => {
    e.preventDefault();

    setIsLogin(true);
    try {
      await login(formData);
      window.location.href = "/";
    } catch (e) {
      console.error("Erreur lors de la connexion", e);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="container d-flex justify-content-center align-items-center vh-100">
      <div className="card p-4 shadow-lg auth-card">
        <h2 className="text-center mb-4">
          {isLogin ? "Connexion" : "Inscription"}
        </h2>
        <div className="btn-group w-100 mb-3">
          <button
            className={`btn ${isLogin ? "btn-primary" : "btn-outline-primary"}`}
            onClick={() => setIsLogin(true)}
          >
            Connexion
          </button>
          <button
            className={`btn ${!isLogin ? "btn-primary" : "btn-outline-primary"}`}
            onClick={() => setIsLogin(false)}
          >
            Inscription
          </button>
        </div>

        {isLogin ? (
          <form onSubmit={handleSubmit}>
              <div className="mb-3">
                  <label className="form-label">Pseudo</label>
                  <input
                      type="text"
                      name="username"
                      className="form-control"
                      placeholder="Votre pseudo"
                      value={formData.username}
                      onChange={handleChange}
                      required
                  />
              </div>
              <div className="mb-3">
                  <label className="form-label">Mot de passe</label>
                  <input
                      type="password"
                      name="password"
                      className="form-control"
                      placeholder="Votre mot de passe"
                      value={formData.password}
                      onChange={handleChange}
                      required
                  />
              </div>
              <button type="submit" className="btn btn-success login-btn" disabled={loading}>
                  {loading ? "Chargement..." : "Se connecter"}
              </button>
          </form>
        ) : (
          <div className="text-center">
            <p>Créez un compte pour accéder à toutes les fonctionnalités.</p>
            <Link to={"/signup"}>
              <div className="btn btn-success w-100">S&apos;inscrire</div>
            </Link>
          </div>
        )}
      </div>
    </div>
  );
}

export default Auth;