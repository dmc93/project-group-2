import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import { useState } from 'react';
import { Link } from 'react-router-dom';

function NavBar() {
  const [expanded, setExpanded] = useState(false);

  return (
    <>
      <Navbar bg="light" expand="lg" className="custom-navbar fixed-top" expanded={expanded}>
        <Container>
          <Navbar.Brand href="/">WrongMove</Navbar.Brand>
          <Navbar.Toggle aria-controls="basic-navbar-nav" onClick={() => setExpanded(expanded ? false : "expanded")} />
          <Navbar.Collapse id="basic-navbar-nav">
            <Nav className="me-auto" onClick={() => setExpanded(false)}>
              <Link className="nav-link" to="/">Home</Link>
              <Link className="nav-link" to="/buyers">Buyers</Link>
              <Link className="nav-link" to="/sellers">Sellers</Link>
              <Link className="nav-link" to="/propertyadmin">Property Admin</Link>
              <Link className="nav-link" to="/appointments">Appointments</Link>
            </Nav>
          </Navbar.Collapse>
        </Container>
      </Navbar>
      <br />
     </>
  );
}

export default NavBar;
