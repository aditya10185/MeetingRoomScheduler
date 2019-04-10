import React from 'react';
import { connect } from 'react-redux';
import {mRoomLoading, mRoomData} from '../../actions/MRoomActions';
import {
  Form,
  FormGroup,
  Button, 
  Label,
  Input
} from 'reactstrap';
import * as DateTime from 'react-datetime';
import {getAvailableRooms} from '../../services';
// import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

class CreateMeeting extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      meetingStartDateTime: null,
      meetingDuration: null,
      capacity: null
    }
  }

  handleStartDate(e) {
    this.setState({
      meetingStartDateTime: e
    });
  }

  handleDuration(e) {
    this.setState({
      meetingDuration: e.target.value
    });
  }

  handleCapacity(e) {
    this.setState({
      capacity: e.target.value
    })
  }



  startTimeConstraints() {
    return {
      hours: { min: 9, max: 19 },
    }
  }

  meetingDuration() {
    return (
      <Input type="select" name="meeting-duration" id="meeting-duration" onChange={(e) => this.handleDuration(e)}>
        <option>Select Duration</option>
        <option value=".5">30 Minutes</option>
        <option value="1">1 Hour</option>
        <option value="1.5">1 Hour 30 Minutes</option>
        <option value="2">2 Hours</option>
        <option value="2.5">2 Hour 30 Minutes</option>
        <option value="3">3 Hours</option>
        <option value="3.5">3 Hour 30 Minutes</option>
        <option value="4">4 Hours</option>
      </Input>
    );
  }

  checkAvailability(e) {
    e.preventDefault();
    this.props.mRoomLoading({
      mRoomLoading: true
    })
    const startDate = this.state.meetingStartDateTime.format('DD.MM.YYYY HH:mm');
    const endDate = this.state.meetingStartDateTime.add(this.state.meetingDuration, 'hours').format('DD.MM.YYYY HH:mm');
    console.log(startDate, endDate);
    getAvailableRooms(startDate, endDate, this.state.capacity, this.props.auth.accessToken).then(res => {
      console.log(res);
      this.props.mRoomData({
        mErr: null,
        mRooms: res.data.meetingRooms,
        meetingStartDateTime: startDate,
        capacity: this.state.capacity,
        duration: this.state.meetingDuration,
        meetingEndDateTime: endDate
      });
      this.props.mRoomLoading({
        mRoomLoading: false
      })
    }).catch(err => {
      this.props.mRoomData({
        mErr: err.response.data.error,
        mRooms: null,
        meetingDateTime: null,
        capacity: null,
        duration: null
      });
      console.log(err.response.data.error);
      this.props.mRoomLoading({
        mRoomLoading: false
      })
    });
  }

  render() {
    return(
      <Form>
        <h3>Meeting Details</h3>
        <FormGroup>
          <Label for="start-date">Meeting start date time:</Label>
          <DateTime onChange={(e) => this.handleStartDate(e)} inputProps={{placeholder: 'Meeting Start Time'}} timeConstraints={this.startTimeConstraints()}/>
        </FormGroup>
        <FormGroup>
          <Label for="meeting-duration">Meeting duration:</Label>
          {this.meetingDuration()}
        </FormGroup>
        <FormGroup>
          <Label for="participants">Number of participants:</Label>
          <Input type="number" onChange={(e) => this.handleCapacity(e)} id="participants" placeholder="eg: 2" min="1" max="10"></Input>
        </FormGroup>
        <div className="text-center">
        <Button outline color="info" onClick= {(e) => this.checkAvailability(e)}>Check Availability</Button>
        </div>
      </Form>
    )
  }
}


const mapStateToProps = state => ({
  auth: state.authReducers
});

const mapDispatchToProps = dispatch => ({
  mRoomLoading: (loadingStatus) => dispatch(mRoomLoading(loadingStatus)),
  mRoomData: (data) => dispatch(mRoomData(data))
});

export default connect(mapStateToProps, mapDispatchToProps)(CreateMeeting);