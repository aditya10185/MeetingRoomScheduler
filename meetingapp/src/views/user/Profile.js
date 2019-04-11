import React from 'react';
import {connect} from 'react-redux';
import {
    Container,
    Card,
    CardBody
} from 'reactstrap';

class UserProfile extends React.Component {

    render() {
        return (
            <Container>
                <Card className="mt-2">
                    <CardBody>
                        <p>Name: {this.props.auth.user.firstName} {this.props.auth.user.lastName}</p>
                        <p>Email Address: {this.props.auth.user.email}</p>
                        <p>User Id: {this.props.auth.user.id}</p>
                        <p>Contact No: {this.props.auth.user.contactNo}</p>
                    </CardBody>
                </Card>
            </Container>
        )
    }

}

const mapStateToProps = state => ({
    auth: state.authReducers,
});

export default connect(mapStateToProps)(UserProfile);