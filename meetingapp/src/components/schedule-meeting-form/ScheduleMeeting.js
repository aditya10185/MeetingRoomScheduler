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

export default class ScheduleMeeting extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            attendees: []
        }
    }

    handleAttendeeChange(e) {
        let emailAddresses = e.target.value.split(';');
        const emailPattern = /^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/;
        const emails = emailAddresses.map((email) => {
            if(email.match(emailPattern)) {
                return email;
            } else return null
        })
        if(emails[0] && emails.length > 0) {

        }
    }

    getValidEmails() {

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
                                {this.getValidEmails()}
                            </p>
                            <Input
                                type="textarea"
                                name="attendees"
                                id="attendees"
                                onChange={(e) =>this.handleAttendeeChange(e)}
                            >
                            </Input>
                        </InputGroup>
                        <FormText color="muted">
                            Enter email addresses separated by a semi-colon (;)
                        </FormText>
                    </FormGroup>
                    <div className="text-center">
                    <Button outline color="info">Schedule</Button>
                    </div>
                </Form>
            </React.Fragment>
        )
    }
}