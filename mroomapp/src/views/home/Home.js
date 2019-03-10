import React, {Component} from 'react';
import {connect} from 'react-redux';

import './Home.scss';

class Home extends Component {
    render() {
        return (
            <div>Hello from Home!</div>
        );
    }
}

export default connect()(Home);