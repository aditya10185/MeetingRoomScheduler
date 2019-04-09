import React from 'react';
import {
  Container,
  Jumbotron,
  Button
} from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

function goBack(){
  window.history.back();
}

const unauthorized = function(props) {
  return (
    <Container className="text-center mt-2">
    <Jumbotron className="bg-light text-dark shadow">
      <FontAwesomeIcon color="#dd2c00" size="3x" icon="exclamation-triangle"></FontAwesomeIcon>
      <h2>Sorry you are not authorized to view this page.</h2>
      <Button outline color="dark" size="lg" onClick={() => goBack()} className="mt-1">Go back</Button>
    </Jumbotron>  
    </Container>
  );
}

export default unauthorized;