import React, { Component } from 'react';
import { connect } from 'react-redux';
import {Button} from 'reactstrap';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import './Home.scss';

import bgVid from '../../assets/bg-vid.mp4';

class Home extends Component {

    checkLogin() {
        if (this.props.auth.accessToken) {
            return (
                <div>
                    <h2 className="banner-title">Welcome {this.props.auth.user.firstName} {this.props.auth.user.lastName}</h2>
                    <h3>Would you like to schedule a meeting today?</h3>
                    <Button outline color="light">Let's Go <FontAwesomeIcon icon="chevron-circle-right"/></Button>
                </div>
            )
        } else {
            return (
                <div>
                    <h2 className="banner-title">Meeting Room Scheduler </h2>
                    <h3>Built using - React.js, Spring Boot, and MySQL</h3>
                    <Button outline color="light">Learn More <FontAwesomeIcon icon="chevron-circle-right"/></Button>
                </div>
            )
        }
    }

    render() {
        return (
            <section className="banner">
                <div className="viewport-header">
                    <div className="fullscreen-video-wrap">
                        <video playsInline autoPlay muted loop>
                            <source src={bgVid} type="video/mp4"></source>
                        </video>
                    </div>
                </div>
                <div className="banner-overlay"></div>
                <div className="text-center text-white container banner-content">
                    {this.checkLogin()}
                </div>
            </section>
        );
    }
}

const mapStateToProps = state => ({
    ...state,
    auth: state.authReducers
})

export default connect(mapStateToProps)(Home);