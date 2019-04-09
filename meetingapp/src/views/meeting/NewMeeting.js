import React from 'react';
// import * as DateTime from 'react-datetime';
import {
  Container,
  Row,
  Col,
  Card,
  CardBody,
  CardHeader
} from 'reactstrap';
import './Meeting.scss';
import CreateMeeting from '../../components/create-meeting/CreateMeeting';
import AvailableRooms from '../../components/available-rooms/AvailableRooms';


export default class MeetingRoom extends React.Component {
  render() {
    return (
      <Container fluid className="mt-2">
        <Card className="heading">
          <CardHeader className="bg-info text-white">
            <h2 className="my-0 text-center">Schedule a new meeting</h2>
          </CardHeader>
          <CardBody>
        <Row className="mt-2">
          <Col sm="12" md="6" className="column-border">
            <CreateMeeting/>
          </Col>
          <Col sm="12" md="6">
            <AvailableRooms/>
          </Col>
        </Row>
        </CardBody>
        </Card>
      </Container>
    );
  }
}