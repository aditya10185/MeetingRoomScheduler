import { USER_LOGIN, USER_LOGOUT } from './ActionTypes';

export const userLogin = (accessToken, user) => ({
    type: USER_LOGIN,
    payload: {
        accessToken: accessToken,
        user: user
    }
});

export const userLogout = () => ({
    type: USER_LOGOUT,
    payload: {
        accessToken: '',
        user: {}
    }
})