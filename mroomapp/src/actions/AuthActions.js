import { USER_LOGIN } from './ActionTypes';

export const userLogin = (accessToken, user) => ({
    type: USER_LOGIN,
    payload: {
        accessToken: accessToken,
        user: user
    }
});