import {instance} from './index';

export const createEmployee = (employee) => {
    return instance.post('/user', employee);
}

export const loginUser = (email, password) => {
    return instance.post('/auth/login', {
        email: email,
        password: password
    });
}

export const getAvailableRooms = (startDateTime, endDateTime, capacity, token) => {
    return instance.post('/meeting-room/available', {
        meetingStartDate: startDateTime,
        meetingEndDate: endDateTime,
        capacity: capacity
    }, {
        headers: {
            authorization: token
        }
    })
}