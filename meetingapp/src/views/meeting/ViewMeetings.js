import React from 'react';
import { connect } from 'react-redux';
import { getScheduledMeetingsForHost } from '../../services';
import * as moment from 'moment';
import {
    Container,
    Card,
    CardBody,
    CardColumns
} from 'reactstrap';

class ViewMeetings extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            meetings: []
        }
    }

    componentWillMount() {
        getScheduledMeetingsForHost(this.props.auth.user.id, this.props.auth.accessToken).then(res => {
            const meetingList = res.data.meetings;
            if (meetingList.length > 0) {
                let meetings = [];
                meetingList.map((curr, index, arr) => {
                    if (index !== 0 && arr[index - 1].meetingId === curr.meetingId) {
                        meetings[meetings.length - 1].attendees.push(curr.attendee);
                        return curr;
                    } else {
                        let meeting = {
                            ...curr,
                            attendees: [
                                curr.attendee
                            ]
                        }
                        meetings[meetings.length] = meeting;
                        return meeting;
                    }
                });
                this.setState({
                    meetings: meetings
                })
            } 
        }).catch(err => {
                console.log(err);
            })
    }

    getMeetings() {
        if(this.state.meetings.length > 0) {
            const meetingMap = this.state.meetings.map((meet) => <Card className="mt-2" key={meet.meetingId}>
                <CardBody>
                    <p>Meeting Id: {meet.meetingId}</p>
                    <p>Host: {meet.hostEmail}</p>
                    <p>Meeting Location: {meet.meetingLocation}</p>
                    <p>Meeting Start Time: {moment(meet.meetingStartDate).format("DD.MM.YY HH.mm A")}</p>
                    <p>Meeting End Time: {moment(meet.meetingEndDate).format("DD.MM.YY HH.mm A")}</p>
                    <p>Meeting Status: {meet.meetingStatus}</p>
                    <p className="mb-1">Attendees: </p>
                    {
                        meet.attendees.map((attendee, index) => <p key={index} className="ml-4 mb-0">{attendee}</p>)
                    }
                </CardBody>
            </Card>)
            return (
                <React.Fragment>
                    {meetingMap}
                </React.Fragment>
            );
            } else {
                return (
                    <Card>
                        <CardBody>
                            <p>Looks like you don't have any meetings scheduled.</p>
                        </CardBody>
                    </Card>
                );
            }
        
    }

    render() {
        return (
            <Container>
                <CardColumns>
                    {this.getMeetings()}
                </CardColumns>
            </Container>
        )
    }
}

const mapStateToProps = state => ({
    auth: state.authReducers,
});

export default connect(mapStateToProps)(ViewMeetings)