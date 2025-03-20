import "./Profile.css";
import { Container, Card, Button } from "react-bootstrap";

const Profile = () => {
  return (
    <Container className="profile-container">
      <Card className="profile-card">
        <Card.Img
          variant="top"
          src="https://via.placeholder.com/150"
          alt="Profile Picture"
          className="profile-img"
        />
        <Card.Body>
          <Card.Title>Nom de l&apos;utilisateur</Card.Title>
          <Card.Text>Email : user@example.com</Card.Text>
          <Button variant="primary">Modifier le profil</Button>
        </Card.Body>
      </Card>
    </Container>
  );
};

export default Profile;