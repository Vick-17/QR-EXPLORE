import ConstructionIcon from "@mui/icons-material/Construction";
import "../Style/pages/UnderDev.css";

const UnderDev = () => {
  return (
    <div className="construction-container">
      <ConstructionIcon className="construction-icon" />
      <h1>Page en cours de developpement</h1>
      <p>Nous travaillons dur pour donner vie à cette page. Restez connectés !</p>
      <div className="loading-spinner"></div>
    </div>
  );
};

export default UnderDev;