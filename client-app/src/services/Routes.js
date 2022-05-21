const host = 'http://localhost:8080';

export class Routes{
    LOGIN = host + '/login';
    REGISTER = host + '/register';
    CITY_LOV = host + '/city/lov';
    GENRE_LOV = host + '/genre/lov';
    
    USER_PROFILE = host + '/user';
    USER_RECOMMENDATIONS = USER_PROFILE + '/recommendations';
    USER_RECOMMENDATIONS_GENRE = (genreId) => USER_RECOMMENDATIONS + `/${genreId}`;
}
export default new Routes();
