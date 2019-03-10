import React, {Component} from 'react';
import {connect} from 'react-redux';
import {
    Container,
    Card
} from 'reactstrap'
import EmployeeLoginForm from '../../components/login-employee-form/EmployeeLoginForm';

class EmployeeLogin extends Component {
    constructor(props) {
        super(props);
        console.log(props);
    }
    render() {
        return (
            <Container className="mt-2">
                <Card className="p-4 shadow border-0">
                    <EmployeeLoginForm></EmployeeLoginForm>
                </Card>
            </Container>
        )
    }
}


export default connect()(EmployeeLogin);