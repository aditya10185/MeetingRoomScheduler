import { MROOM_LOADING, MROOM_DATA } from './ActionTypes';

export const mRoomLoading = (loader) => ({
    type: MROOM_LOADING,
    payload: loader
});

export const mRoomData = (data) => ({
    type: MROOM_DATA,
    payload: data
});