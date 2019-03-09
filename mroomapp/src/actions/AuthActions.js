import { USER_LOGIN } from './ActionTypes';

export const userLogin = (accessToken, email) => ({
    type: USER_LOGIN,
    payload: {
        accessToken: accessToken,
        email: email
    }
});