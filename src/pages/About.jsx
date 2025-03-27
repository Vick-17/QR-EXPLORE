// import React from "react";
import "../Style/pages/About.css"; // Import du fichier CSS

function About() {
  return (
    <div className="container my-5">
      <h1 className="text-center mb-4">À propos de QR-EXPLORE</h1>
      <div className="card p-4 shadow-lg">
        <h2>Notre Histoire</h2>
        <p>
          L&apos;idée de QR-EXPLORE est née d&apos;une frustration commune : trop souvent,
          les guides touristiques traditionnels passent à côté de lieux uniques et
          authentiques, connus seulement des habitants. Nous voulions créer un
          outil qui permette aux voyageurs de découvrir ces endroits cachés tout
          en valorisant les connaissances des locaux.
        </p>

        <h2>Le Concept</h2>
        <p>
          QR-EXPLORE permet aux villes, offices de tourisme et habitants de
          générer des QR codes liés à des descriptions de lieux incontournables.
          En scannant ces codes, les touristes peuvent découvrir des anecdotes,
          des histoires et des recommandations qui ne figurent pas dans les guides
          classiques.
        </p>

        <h2>Pourquoi QR-EXPLORE ?</h2>
        <ul>
          <li>Découvrir des lieux méconnus et authentiques.</li>
          <li>Faciliter l&apos;accès aux informations locales via un simple scan.</li>
          <li>Encourager une exploration plus immersive et interactive.</li>
          <li>Donner la parole aux habitants pour partager leur patrimoine.</li>
        </ul>

        <h2>Notre Vision</h2>
        <p>
          Nous croyons en un tourisme plus humain et plus connecté aux réalités
          locales. QR-EXPLORE est un guide collaboratif qui met en avant la
          richesse culturelle et historique d’une région à travers les yeux de
          ceux qui la vivent au quotidien.
        </p>
      </div>
    </div>
  );
}

export default About;
