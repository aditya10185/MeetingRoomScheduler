import React, { Component } from 'react';
import {BrowserRouter as Router, Route} from 'react-router-dom';
import {connect} from 'react-redux';
import { library } from '@fortawesome/fontawesome-svg-core';
import {faUserCircle, faCalendarAlt, faChevronCircleRight, faCalendarCheck, faUser, faLock, faCalendarPlus} from '@fortawesome/free-solid-svg-icons';
import Home from './views/home/Home';
import './App.scss';
import Navbar from './components/navbar/Navbar';
import { userLogin } from './actions/AuthActions';

library.add(faUserCircle, faCalendarAlt, faChevronCircleRight, faCalendarCheck, faUser, faLock, faCalendarPlus);

class App extends Component {

  render() {
    return (
      <div className="App">
      <Navbar></Navbar>
      <Router>
        <div> 
          <Route path="/" exact component={Home}/>
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
