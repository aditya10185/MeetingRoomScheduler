import { combineReducers } from 'redux';

import authReducers from './AuthReducer';
import mRoomReducers from './MRoomReducer';


export default combineReducers({
    authReducers,
    mRoomReducers
})