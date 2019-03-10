import React, { Component } from 'react';
import {BrowserRouter as Router, Route} from 'react-router-dom';
import {connect} from 'react-redux';
import Home from './views/home/Home';
import './App.scss';
import Navbar from './components/navbar/Navbar';
import CreateEmployee from './views/create-employee/CreateEmployee';
import EmployeeLogin from './views/employee-login/EmployeeLogin';
import { userLogin } from './actions/AuthActions';



class App extends Component {

  render() {
    return (
      <div className="App">
      <Navbar></Navbar>
      <Router>
        <div> 
          <Route path="/" exact component={Home}/>
          <Route path="/create-employee" exact component={CreateEmployee}/>
          <Route path="/login-employee" exact component={EmployeeLogin}/>
        </div> 
      </Router>
      </div>
    );
  }
}

const mapStateToProps = state => ({
  ...state
})

const mapDispatchToProps = dispatch => ({
  USER_LOGIN: dispatch(userLogin)
})

export default connect(mapStateToProps, mapDispatchToProps)(App);
