import React, { Component } from 'react';
import { connect } from 'react-redux';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import {
  Collapse,
  Navbar,
  NavbarToggler,
  NavbarBrand,
  Nav,
  // NavItem,
  // NavLink,
  Modal,
  ModalHeader,
  ModalBody,
  ModalFooter,
  UncontrolledDropdown,
  DropdownToggle,
  DropdownMenu,
  Button,
  DropdownItem
} from 'reactstrap';
import { userLogout } from '../../actions/AuthActions';
import EmployeeLoginForm from '../login-employee-form/EmployeeLoginForm';
import CreateEmployeeForm from '../create-employee-form/CreateEmployeeForm';

class Header extends Component {
  constructor(props) {
    super(props);

    this.toggle = this.toggle.bind(this);
    this.state = {
      isOpen: false,
      modal: false,
      modalBody: 'login'
    };
  }

  toggle() {
    this.setState({
      isOpen: !this.state.isOpen
    });
  }

  logout() {
    if (this.props.auth.accessToken) {
      this.props.userLogout();
      this.userOptions();
    }
  }

  toggleModalBody(value) {
    this.setState({
      modalBody: value
    });
  }

  userOptions() {
    if (this.props.auth.accessToken) {
      return (
        <DropdownMenu right>
          <DropdownItem href="/profile">
            <FontAwesomeIcon icon="user" /> User Profile
        </DropdownItem>
          <DropdownItem divider />
          <DropdownItem onClick={() => this.logout()}>
            <FontAwesomeIcon icon="lock" /> Logout
          </DropdownItem>
        </DropdownMenu>
      )
    } else {
      return (
        <DropdownMenu right>
          <DropdownItem onClick={() => this.toggleModal()}>
            Login/Register
          </DropdownItem>
        </DropdownMenu>
      )
    }
  }

  toggleModal() {
    this.setState(prevState => ({
      modal: !prevState.modal
    }));
  }

  getModalBody() {
    switch (this.state.modalBody) {
      case 'login': {
        return (
          <div>
            <ModalBody>
              <EmployeeLoginForm modal={() => this.toggleModal()}></EmployeeLoginForm>
            </ModalBody>
            <ModalFooter>
              <Button size="sm" color="success" onClick={() => this.toggleModalBody('register')}>Don't have an account?</Button>
            </ModalFooter>
          </div>
        )
      }
      case 'register': {
        return (
          <div>
            <ModalBody>
              <CreateEmployeeForm modal={() => this.toggleModal()}></CreateEmployeeForm>
            </ModalBody>
            <ModalFooter>
              <Button size="sm" color="success" onClick={() => this.toggleModalBody('login')}>Already have an account?</Button>
            </ModalFooter>
          </div>
        )
      }
      default: {}
    }
  }

  getSchedulerOptions() {
    if (this.props.auth.accessToken) {
      return (
        <UncontrolledDropdown nav inNavbar>
          <DropdownToggle nav>
            <FontAwesomeIcon color="white" size="2x" icon="calendar-alt"></FontAwesomeIcon>
          </DropdownToggle>
          <DropdownMenu right>
          <DropdownItem href="/meeting/new">
            <FontAwesomeIcon icon="calendar-plus"/> Schedule New Meeting
          </DropdownItem>
          <DropdownItem href="/meeting/view">
            <FontAwesomeIcon icon="calendar-alt"/> View Scheduled Meetings
          </DropdownItem>
          </DropdownMenu>
        </UncontrolledDropdown>
      )
    }
  }

  getNavColor() {
    const currentRoute = window.location.pathname;
    if(currentRoute === '/' ) {
      return 'faded';
    } else return 'primary';
  }

  render() {
    return (
      <div>
        <Navbar color={this.getNavColor()} dark expand="md">
          <NavbarBrand href="/" className="text-white"><FontAwesomeIcon icon="handshake" className="d-inline-block align-middle mr-2" size="2x" color="#F0D5BE"/>Meeting Room Application</NavbarBrand>
          <NavbarToggler onClick={this.toggle} />
          <Collapse isOpen={this.state.isOpen} navbar>
            <Nav className="ml-auto" navbar>
              {/* <NavItem>
                <NavLink href="/">Home</NavLink>
              </NavItem> */}
              {this.getSchedulerOptions()}
              <UncontrolledDropdown nav inNavbar>
                <DropdownToggle nav>
                  <FontAwesomeIcon color="white" size="2x" icon="user-circle"></FontAwesomeIcon>
                </DropdownToggle>
                {this.userOptions()}
              </UncontrolledDropdown>
            </Nav>
          </Collapse>
          <Modal isOpen={this.state.modal} centered toggle={() => this.toggleModal()}>
            <ModalHeader toggle={() => this.toggleModal()}>
              Login/Register
            </ModalHeader>
            {this.getModalBody()}
          </Modal>
        </Navbar>
      </div>
    );
  }
}

const mapStateToProps = state => ({
  auth: state.authReducers
})

const mapDispatchToProps = dispatch => ({
  userLogout: () => dispatch(userLogout())
})

export default connect(mapStateToProps, mapDispatchToProps)(Header);