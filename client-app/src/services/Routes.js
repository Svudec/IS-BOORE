const host = 'http://localhost:8080';


export const LOGIN = host + '/login';
export const REGISTER = host + '/register';
export const CITY_LOV = host + '/city/lov';
export const GENRE_LOV = host + '/genre/lov';

export const USER_PROFILE = host + '/user';
export const USER_RECOMMENDATIONS = USER_PROFILE + '/recommendations';
export const USER_RECOMMENDATIONS_GENRE = (genreId) => USER_RECOMMENDATIONS + `/${genreId}`;

