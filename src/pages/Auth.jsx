import { useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import "../Style/pages/Auth.css"
import { login } from "../service/BackendService.js"
import { Link } from "react-router-dom";

function Auth() {
  const [isLogin, setIsLogin] = useState(true);
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const handleSubmit = async (e) => {
    // e.preventDefault();
    // setError("");

    // try {
    //   const response = await login({email, password});
    //   if (response.succes) {
    //     console.log("Connexion Reussie");
    //   } else {
    //     setError(response.message);
    //   }
    // } catch (err) {
    //   setError("Une erreur est survenue : ", err);
    // }
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
              {error && <div className="alert alert-danger">{error}</div>}
              <div className="mb-3">
                  <label className="form-label">Email</label>
                  <input
                      type="email"
                      className="form-control"
                      placeholder="Votre email"
                      value={email}
                      onChange={(e) => setEmail(e.target.value)}
                      required
                  />
              </div>
              <div className="mb-3">
                  <label className="form-label">Mot de passe</label>
                  <input
                      type="password"
                      className="form-control"
                      placeholder="Votre mot de passe"
                      value={password}
                      onChange={(e) => setPassword(e.target.value)}
                      required
                  />
              </div>
              <button type="submit" className="btn btn-success login-btn">
                  Se connecter
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