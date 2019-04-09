import {MROOM_LOADING, MROOM_DATA, USER_LOGOUT} from '../actions/ActionTypes';

const INITIAL_STATE = {
    mRoomLoading: false,
    mRooms: [],
    mErr: null,
    meetingStartDateTime: null,
    meetingEndDateTime: null,
    capacity: null,
    duration: null
}

export default function (state = INITIAL_STATE, action) {
    switch(action.type) {
        case MROOM_LOADING: {
            const {mRoomLoading} = action.payload;
            return {
                ...state,
                mRoomLoading
            }
        } 
        case MROOM_DATA: {
            const {mErr, mRooms, meetingStartDateTime, meetingEndDateTime, capacity, duration} = action.payload;
            return {
                ...state,
                mErr,
                mRooms,
                meetingStartDateTime,
                meetingEndDateTime,
                capacity,
                duration
            }
        } case USER_LOGOUT: {
            const mErr = null;
            const mRooms = [];
            const meetingStartDateTime = null;
            const meetingEndDateTime = null;
            const capacity = null;
            const duration = null;
            return {
                ...state,
                mErr,
                mRooms,
                meetingStartDateTime,
                meetingEndDateTime,
                capacity,
                duration
            }
        } default: return state;
    }
}