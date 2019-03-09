import {USER_LOGIN} from '../actions/ActionTypes';

const INITIAL_STATE = {
    accessToken: '',
    user: {}
}

export default function(state = INITIAL_STATE, action) {
    switch(action.type) {
        case USER_LOGIN: {
            const {accessToken, email} = action.payload;
            return {
                ...state, 
                accessToken
            };
        }
        default: 
            return state;
    }
}