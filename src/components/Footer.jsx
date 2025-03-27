import { Container, Row, Col } from "react-bootstrap";
import { Link } from "react-router-dom";

import "../Style/components/Footer.css"

const Footer = () => {
  return (
    <footer className="footer text-white py-3">
      <Container>
        <Row className="justify-content-between">
          <Col md={6}>
            <p className="mb-0 footer-items">© {new Date().getFullYear()} QR-EXPLORE. Tous droits réservés.</p>
          </Col>
          <Col md={6} className="text-md-end">
            <Link to="/mentions-legales" className="me-3 footer-items footer-link">Mentions légales</Link>
            <Link to="/cookies" className="footer-items footer-link">Gestion des cookies</Link>
          </Col>
        </Row>
      </Container>
    </footer>
  );
};

export default Footer;
