import React, {Component} from 'react';
import {connect} from 'react-redux';
import {
    Button,
    Form,
    FormGroup,
    Label,
    Input,
    Alert
    // FormText
} from 'reactstrap';

// import {userLogin} from '../../actions/AuthActions';

import {loginUser} from '../../services';
import { USER_LOGIN } from '../../actions/ActionTypes';

class EmployeeLogin extends Component {
    constructor(props) {
        super(props);
        this.state = {
            email: '',
            password: '',
            error: false,
            errMsg: ''
        };
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

    handleSubmit(e) {
        loginUser(this.state.email, this.state.password).then(res => {
            console.log(res.data);
            console.log(this.props)
            this.props.userLogin({
                accessToken: res.data.token, 
                user:res.data.user
            });
        }).catch(err => {
            this.setState({
                error: true,
                errMsg: err.response.data.error
            })
            console.log(err);
        })
    }

    getAlert() {
        if(this.state.error) {
            return (<Alert color="danger">{this.state.errMsg}</Alert>);
        } else return;
    }

    render() {
        return (
            <Form>
                {this.getAlert()}
                <FormGroup>
                    <Label for="email">Email</Label>
                    <Input type="email" id="email" placeholder="Eg. johndoe@example.com" required value={this.state.email} onChange={(e) => this.handleEmailChange(e)}></Input>
                </FormGroup>
                <FormGroup>
                    <Label for="password">Enter password:</Label>
                    <Input type="password" id="password" placeholder="Eg. password" required value={this.state.password} onChange={(e) => this.handlePasswordChange(e)}></Input>
                </FormGroup>
                <Button outline color="danger" onClick={() => this.handleSubmit()}>Login</Button>
            </Form>
        );
    }
}
const mapStateToProps = state => ({
    ...state
})

const mapDispatchToProps = dispatch => ({
    userLogin: (payload) => dispatch({type: USER_LOGIN, payload: payload})
})

export default connect(mapStateToProps, mapDispatchToProps)(EmployeeLogin);