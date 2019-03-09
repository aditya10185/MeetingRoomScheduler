import React, { Component } from 'react';
import {BrowserRouter as Router, Route} from 'react-router-dom';
import {Provider, connect} from 'react-redux';
import { PersistGate } from 'redux-persist/integration/react';
import Home from './views/home/Home';
import './App.scss';
import Navbar from './components/navbar/Navbar';
import CreateEmployee from './views/create-employee/CreateEmployee';
import EmployeeLogin from './views/employee-login/EmployeeLogin';
import ConfigureStore from './ConfigureStore';

const {persistor, store} = ConfigureStore();

class App extends Component {

  render() {
    return (
      <Provider store={store}>
      <PersistGate loading={null} persistor={persistor}>
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
      </PersistGate>
      </Provider>
    );
  }
}

export default App;
