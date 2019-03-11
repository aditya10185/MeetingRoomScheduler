import {USER_LOGIN, USER_LOGOUT} from '../actions/ActionTypes';

const INITIAL_STATE = {
    accessToken: '',
    user: {}
}

export default function(state = INITIAL_STATE, action) {
    switch(action.type) {
        case USER_LOGIN: {
            const {accessToken, user} = action.payload;
            return {
                ...state, 
                accessToken,
                user
            };
        }
        case USER_LOGOUT: {
            const {accessToken, user} = action.payload;
            return {
                ...state,
                accessToken,
                user
            }
        }
        default: 
            return state;
    }
}