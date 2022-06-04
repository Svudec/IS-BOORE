const host = 'http://localhost:8080';
//const host = 'https://is2022-boore.herokuapp.com';


export const LOGIN = host + '/login';
export const REGISTER = host + '/register';

export const CITY_LOV = host + '/city/lov';
export const GENRE_LOV = host + '/genre/lov';
export const BOOK_LOV = host + '/book/lov';

export const USER_PROFILE = (userId) => host + `/user${userId ? '/' + userId : ''}`;
export const USER_RECOMMENDATIONS = (genreId) => USER_PROFILE() + `/recommendations${genreId ? '?genreId=' + genreId : ''}`;

export const WISHLIST = (bookId, wasRead) => host + `/wishlist?bookId=${bookId}${wasRead != null ? '&wasRead=' + wasRead : ''}`;

export const REVIEW = (bookId) => host + `/review${bookId ? '?bookId=' + bookId : ''}`;

export const BOOK = (bookId) => host + `/book/${bookId}`;

export const BOOK_SEARCH = (searchString) => host + '/book/search?title=' + searchString;

export const AUTHOR = (authorId) => host + `/author/${authorId}`;

