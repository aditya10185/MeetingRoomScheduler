import React, { Component } from 'react';
import { connect } from 'react-redux';

import './Home.scss';

import bgVid from '../../assets/bg-vid.mp4';

class Home extends Component {

    checkLogin() {
        if (this.props.auth.accessToken) {
            return (
                <div>Hi {this.props.auth.user.firstName} {this.props.auth.user.lastName}, How are you doing?</div>
            )
        } else {
            return (
                <div>Hi, How are you doing?</div>
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
                <div className="text-center banner-content">
                    <h2> Meeting Room Scheduler </h2>
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