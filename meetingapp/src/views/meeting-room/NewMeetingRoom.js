import React from 'react';
import { connect } from 'react-redux';
import {
    Container,
    Form,
    Card,
    CardBody,
    FormGroup,
    Input,
    Button,
    Label
} from 'reactstrap';
import {createNewMeetingRoom} from '../../services';

class NewMeetingRoom extends React.Component {

    constructor(props){
        super(props);
        this.state = {
            name: '',
            capacity: '',
            location: ''
        }
    }

    handleName(e) {
        this.setState({
            name: e.target.value
        })
    }

    handleCapacity(e) {
        this.setState({
            capacity: e.target.value
        })
    }

    handleLocation(e) {
        this.setState({
            location: e.target.value
        })
    }

    handleSubmit() {
        createNewMeetingRoom(this.props.auth.accessToken, this.state.name, this.state.location, this.state.capacity).then(res => {
            console.log(res);
        }).catch(err => {
            console.log(err);
        })
    }

    render() {
        return (
            <Container>
                <Card className="mt-2">
                    <CardBody>
                        <Form>
                            <FormGroup>
                                <Label for="name">Room Name:</Label>
                                <Input id="name" onChange={(e) => this.handleName(e)} type="text" placeholder="eg. Meeting Room 1"></Input>
                            </FormGroup>
                            <FormGroup>
                                <Label for="capacity">Room capacity:</Label>
                                <Input id="capacity" onChange={(e) => this.handleCapacity(e)} type="number" min="1" max="10" placeholder="eg. 5"></Input>
                            </FormGroup>
                            <FormGroup>
                                <Label for="location">Room location:</Label>
                                <Input id="location" onChange={(e) => this.handleLocation(e)} type="text" placeholder="eg. Meeting Room 1"></Input>
                            </FormGroup>
                            <div className="text-center">
                                <Button outline onClick={() => this.handleSubmit()} color="primary">
                                    Create
                                </Button>
                            </div>
                        </Form>
                    </CardBody>
                </Card>
            </Container>
        )
    }

}

const mapStateToProps = state => ({
    auth: state.authReducers,
});

export default connect(mapStateToProps)(NewMeetingRoom)