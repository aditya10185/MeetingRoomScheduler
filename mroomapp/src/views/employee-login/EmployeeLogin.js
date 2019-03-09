import React, {Component} from 'react';
import {
    Container,
    Card
} from 'reactstrap'
import EmployeeLoginForm from '../../components/login-employee-form/EmployeeLoginForm';

export default class EmployeeLogin extends Component {
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