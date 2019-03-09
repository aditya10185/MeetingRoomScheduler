import React, {Component} from 'react';
import {
    Container,
    Card
} from 'reactstrap'
import CreateEmployeeForm from '../../components/create-employee-form/CreateEmployeeForm';

export default class CreateEmployee extends Component {
    render() {
        return (
            <Container className="mt-2">
                <Card className="p-4 shadow border-0">
                    <CreateEmployeeForm></CreateEmployeeForm>
                </Card>
            </Container>
        )
    }
}