import axios from 'axios';

// const instance = axios.create({
//     baseUrl: 'http://localhost:8080',
//     headers: {
//         'Content-Type': 'application/json'
//     }
// });

export const createEmployee = (employee) => {
    return axios.post('http://localhost:8080/employee', employee);
}

export const loginUser = (email, password) => {
    return axios.post('http://localhost:8080/auth/login', {
        email: email,
        password: password
    });
}