import React, {Component} from 'react';

import {
    Button,
    Form,
    FormGroup,
    Label,
    Input,
    // FormText
} from 'reactstrap';

import {createEmployee} from '../../services';

export default class CreateEmployeeForm extends Component {
    constructor(props) {
        super(props);
        this.state = {
            firstName: '',
            lastName: '',
            email: '',
            password: '',
            rePassword: '',
            contactNo: ''
        }
    }

    handleFirstNameChange(event) {
        this.setState({
            firstName: event.target.value
        });
    }

    handleLastNameChange(event) {
        this.setState({
            lastName: event.target.value
        });
    }

    handleEmailChange(event) {
        this.setState({
            email: event.target.value
        });
    }

    handlePasswordChange(event) {
        this.setState({
            password: event.target.value
        });
    }

    handleRePasswordChange(event) {
        this.setState({
            rePassword: event.target.value
        });
    }

    handleContactNoChange(event) {
        this.setState({
            contactNo: event.target.value
        });
    }

    handleSubmit(event) {
        const employee = {
            firstName: this.state.firstName,
            lastName: this.state.lastName,
            email: this.state.email,
            password: this.state.password,
            contactNo: this.state.contactNo
        }

        createEmployee(employee).then(res => {
            console.log(res)
        }).catch(err => {
            console.log(err)
        })
    }


    render() {
        return (
            <Form>
                <h2>
                    Register:
                </h2>
                <hr/>   
                <FormGroup>
                    <Label for="firstName">Enter First Name:</Label>
                    <Input type="text" id="firstName" placeholder="Eg. John" required value={this.state.firstName} onChange={(e) => this.handleFirstNameChange(e)}></Input>
                </FormGroup>
                <FormGroup>
                    <Label for="lastName">Enter Last Name:</Label>
                    <Input type="text" id="lastName" placeholder="Eg. Doe" required value={this.state.lastName} onChange={(e) => this.handleLastNameChange(e)}></Input>
                </FormGroup>
                <FormGroup>
                    <Label for="email">Enter email:</Label>
                    <Input type="email" id="email" placeholder="Eg. johndoe@example.com" required value={this.state.email} onChange={(e) => this.handleEmailChange(e)}></Input>
                </FormGroup>
                <FormGroup>
                    <Label for="password">Enter password:</Label>
                    <Input type="password" id="password" placeholder="Eg. password" required value={this.state.password} onChange={(e) => this.handlePasswordChange(e)}></Input>
                </FormGroup>
                <FormGroup>
                    <Label for="re-password">Re-enter password:</Label>
                    <Input type="password" id="re-password" placeholder="Eg. password" required value={this.state.rePassword} onChange={(e) => this.handleRePasswordChange(e)}></Input>
                </FormGroup>
                <FormGroup>
                    <Label for="contact">Enter contact no:</Label>
                    <Input type="text" id="contact" placeholder="Eg. 124567890" required value={this.state.contactNo} onChange={(e) => this.handleContactNoChange(e)}></Input>
                </FormGroup>
                <Button outline color="danger" onClick={() => this.handleSubmit()}>Submit</Button>
            </Form>
        )
    }
}