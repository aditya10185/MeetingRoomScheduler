import React from 'react';
import { Route, Redirect } from 'react-router-dom';
import { connect } from 'react-redux';

const privateRoute = ({component: Component, accessToken, ...rest}) => (
  <Route {...rest}
    render={
      props => accessToken ? (
        <Component {...props}></Component>
      ): <Redirect to={{
        pathname: '/unauthorized',
        state: { from: props.location }
      }}/>
    }
  ></Route>
)

const mapStateToProps = state => ({
  auth: state.authReducers
})

export default connect(mapStateToProps)(privateRoute);
