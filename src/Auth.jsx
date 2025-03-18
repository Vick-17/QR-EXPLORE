import { useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import "./Style/Auth.css";

function Auth() {
  const [isLogin, setIsLogin] = useState(true);

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
          <form>
            <div className="mb-3">
              <label className="form-label">Email</label>
              <input type="email" className="form-control" placeholder="Votre email" />
            </div>
            <div className="mb-3">
              <label className="form-label">Mot de passe</label>
              <input type="password" className="form-control" placeholder="Votre mot de passe" />
            </div>
            <button className="btn btn-success w-100">Se connecter</button>
          </form>
        ) : (
          <div className="text-center">
            <p>Créez un compte pour accéder à toutes les fonctionnalités.</p>
            <button className="btn btn-success w-100">S inscrire</button>
          </div>
        )}
      </div>
    </div>
  );
}

export default Auth;