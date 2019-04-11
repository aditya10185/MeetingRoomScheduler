import React from 'react';
import { connect } from 'react-redux';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Table, Modal, ModalBody, ModalHeader } from 'reactstrap';
import './AvailableRooms.scss';
import ScheduleMeeting from '../schedule-meeting-form/ScheduleMeeting';

class AvailableRooms extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
          modal: false,
          room: {}
        };
      }

    scheduleMeeting(room) {
        this.setState({
            room: room
        })
        this.toggleScheduleModal();
    }

    toggleScheduleModal() {
        this.setState(prevState => ({
            modal: !prevState.modal
          }));
    }

    getLoader() {
        if (this.props.mroom.mRoomLoading) {
            return (
                <div className="text-center text-info">
                    <FontAwesomeIcon icon="sync" spin size="5x"></FontAwesomeIcon>
                </div>
            )
        } else if (this.props.mroom.mErr) {
            return (
                <div className="text-center text-danger">
                    <FontAwesomeIcon icon="exclamation-triangle" size="3x"></FontAwesomeIcon>
                    <h3>{this.props.mroom.mErr}</h3>
                </div>
            )
        } else if (!this.props.mroom.mErr && this.props.mroom.mRooms.length === 0) {
            return (
                <div className="text-center text-info">
                    <FontAwesomeIcon icon="hand-point-left" size="5x" className="mt-4"></FontAwesomeIcon>
                    <h3 className="mt-2">Check for available rooms</h3>
                </div>
            )
        } else if (this.props.mroom.mRooms.length > 0 && !this.props.mroom.mErr) {
            const headersArr = Object.keys(this.props.mroom.mRooms[0]);
            const headers = headersArr.map((header) => <th key={header}>{header.toUpperCase()}</th>);
            const fields = this.props.mroom.mRooms;
            const rows = fields.map((field, index) => {
                let val = []
                headers.forEach(header => {
                    val.push(field[header.key])
                })
                const row = val.map((v, i) => <td key={i}>{v}</td>) 
                return (
                    <tr className="table-row-hover" onClick={() => this.scheduleMeeting(field)} key={index}>
                        {row}
                    </tr>
                )
            })
            return (
                <React.Fragment>
                <h5 className="mb-2 text-muted">Room availability for <span className="text-info">Date - {this.props.mroom.meetingStartDateTime} Duration - ({this.props.mroom.duration} hours)</span> :</h5>
                <Table size="sm" hover>
                    <thead>
                        <tr>
                            {headers}
                        </tr>
                    </thead>
                    <tbody>
                        {rows}
                    </tbody>
                </Table>
                </React.Fragment>
            )
        }

    }
    render() {
        return (
            <React.Fragment>
                {this.getLoader()}
                <Modal centered isOpen={this.state.modal} toggle={() => this.toggleScheduleModal()}>
                    <ModalHeader>
                        Schedule Meeting
                    </ModalHeader>
                    <ModalBody>
                        <ScheduleMeeting 
                            participants={this.props.mroom.capacity} 
                            maxCapacity={this.state.room.capacity} 
                            host={this.props.auth.user}
                            token={this.props.auth.accessToken} 
                            meetingStartTime={this.props.mroom.meetingStartDateTime}
                            meetingEndTime={this.props.mroom.meetingEndDateTime}
                            mroom={this.state.room}
                            modalToggle={() => this.toggleScheduleModal()}
                        />
                    </ModalBody>
                </Modal>
            </React.Fragment>
        )
    }
}

const mapStateToProps = state => ({
    auth: state.authReducers,
    mroom: state.mRoomReducers
});

export default connect(mapStateToProps)(AvailableRooms)