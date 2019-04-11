import React from 'react';
import {
    Form,
    FormGroup,
    Label,
    Input,
    InputGroup,
    FormText,
    Button
} from 'reactstrap';
import { scheduleMeeting } from '../../services';

export default class ScheduleMeeting extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            attendees: [],
            loading: false
        }
    }

    handleAttendeeChange(e) {
        let emailAddresses = e.target.value.split(';');
        const emailPattern = /^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/;
        const emails = emailAddresses.map((email) => {
            if (email.match(emailPattern)) {
                return email;
            } else return null;
        })
        this.setState({
            attendees: emails
        })
    }

    scheduleNewMeeting() {
        if (this.state.attendees[0] && this.state.attendees.length < this.props.mroom.capacity) {
            this.loader();
            scheduleMeeting(this.props.host.id,
                this.props.host.email,
                this.state.attendees,
                this.props.meetingStartTime,
                this.props.meetingEndTime,
                this.props.mroom.location,
                this.props.mroom.id,
                this.props.token).then(res => {
                    console.log(res);
                    alert(res.data.message);
                    this.props.modalToggle();
                }).catch(err => {
                    console.log(err);
                })
        } else alert('You have too many attendees');
    }

    loader() {
        this.setState(prevState => ({
            loading: !prevState.loading
        }))
    }

    render() {
        return (
            <React.Fragment>
                <Form>
                    <div>
                        <p>
                            Meeting Host: {this.props.host.firstName} {this.props.host.lastName}
                        </p>
                        <p>
                            Meeting Start Time: {this.props.meetingStartTime}
                        </p>
                        <p>
                            Meeting End Time: {this.props.meetingEndTime}
                        </p>
                        <p>
                            Meeting location: {this.props.mroom.location}
                        </p>
                    </div>
                    <FormGroup>
                        <Label for="Attendees">Attendees:</Label>
                        <InputGroup>
                            <p className="emails">
                                {/* {this.getValidEmails()} */}
                            </p>
                            <Input
                                type="textarea"
                                name="attendees"
                                id="attendees"
                                onChange={(e) => this.handleAttendeeChange(e)}
                            >
                            </Input>
                        </InputGroup>
                        <FormText color="muted">
                            Enter email addresses separated by a semi-colon (;)
                        </FormText>
                    </FormGroup>
                    <div className="text-center">
                        <Button outline onClick={() => this.scheduleNewMeeting()} disabled={this.state.loading} color="info">Schedule</Button>
                    </div>
                </Form>
            </React.Fragment>
        )
    }
}