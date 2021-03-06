import axios from 'axios';
import {createEmployee, loginUser, getAvailableRooms, getScheduledMeetingsForHost, createNewMeetingRoom, scheduleMeeting} from './services'


const instance = axios.create({
    baseURL: '/api',
    headers: {
        'Content-Type': 'application/json'
    },
    timeout: 60000
});

export {createEmployee, loginUser, instance, getAvailableRooms, scheduleMeeting, createNewMeetingRoom, getScheduledMeetingsForHost};