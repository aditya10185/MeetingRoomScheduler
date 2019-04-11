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

export const createNewMeetingRoom = (token, name, location, capacity) => {
    return instance.post('/meeting-room', {
        name: name,
        location: location,
        capacity: capacity
    }, {
        headers: {
            authorization: token
        }
    })
}

export const scheduleMeeting = (hostId, hostEmail, attendees, meetingStartDate, meetingEndDate, meetingLocation, meetingRoomId, token) => {
    return instance.post('/meeting', {
        hostId,
        hostEmail,
        attendees,
        meetingStartDate,
        meetingEndDate,
        meetingLocation,
        meetingRoomId
    }, {
        headers: {
            authorization: token
        }
    })
}

export const getScheduledMeetingsForHost = (hostId, token) => {
    return instance.get(
        `/meeting/host/${hostId}`,
        {
            headers: {
                authorization: token
            }
        }
    )
}