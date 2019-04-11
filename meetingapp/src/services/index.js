import axios from 'axios';
import {createEmployee, loginUser, getAvailableRooms, getScheduledMeetingsForHost, scheduleMeeting} from './services'


const instance = axios.create({
    baseURL: 'http://localhost:8080/api',
    headers: {
        'Content-Type': 'application/json'
    },
    timeout: 60000
});

export {createEmployee, loginUser, instance, getAvailableRooms, scheduleMeeting, getScheduledMeetingsForHost};