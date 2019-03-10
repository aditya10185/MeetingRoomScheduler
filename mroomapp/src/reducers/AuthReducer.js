import {USER_LOGIN} from '../actions/ActionTypes';

const INITIAL_STATE = {
    accessToken: '',
    user: {}
}

export default function(state = INITIAL_STATE, action) {
    switch(action.type) {
        case USER_LOGIN: {
            console.log(action);
            const {accessToken, user} = action.payload;
            console.log(accessToken, user);
            return {
                ...state, 
                accessToken,
                user
            };
        }
        default: 
            return state;
    }
}