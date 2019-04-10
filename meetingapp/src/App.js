import React, { Component } from 'react';
import {BrowserRouter as Router, Route} from 'react-router-dom';
import {connect} from 'react-redux';
import { library } from '@fortawesome/fontawesome-svg-core';
import {faUserCircle, faCalendarAlt, faChevronCircleRight, faHandPointLeft, faSync,faCalendarCheck, faUser, faLock, faCalendarPlus, faExclamationTriangle, faHandshake} from '@fortawesome/free-solid-svg-icons';
import Home from './views/home/Home';
import NewMeeting from './views/meeting/NewMeeting';
import ViewMeeting from './views/meeting/ViewMeetings';
import './App.scss';
import Navbar from './components/navbar/Navbar';
import { userLogin } from './actions/AuthActions';
import PrivateRoute from './components/private-route/PrivateRoute';
import unauthorized from './views/unauthorized/Unauthorized';

library.add(faUserCircle, faCalendarAlt, faChevronCircleRight, faHandPointLeft, faSync, faCalendarCheck, faUser, faLock, faCalendarPlus, faExclamationTriangle, faHandshake);

class App extends Component {
  componentWillMount() {
    console.log('hereh')
  }
  render() {
    return (
      <div className="App">
      <Navbar></Navbar>
      <Router>
        <div> 
          <Route path="/" exact component={Home}/>
          <Route path="/unauthorized" exact component = {unauthorized}/>
          <PrivateRoute exact path="/meeting/new" accessToken={this.props.auth.accessToken} component={NewMeeting}/>
          <PrivateRoute exact path="/meeting/view" accessToken={this.props.auth.accessToken} component={ViewMeeting}/>
        </div> 
      </Router>
      </div>
    );
  }
}


const mapStateToProps = state => ({
  ...state,
  auth: state.authReducers
})

const mapDispatchToProps = dispatch => ({
  USER_LOGIN: dispatch(userLogin)
})

export default connect(mapStateToProps, mapDispatchToProps)(App);
