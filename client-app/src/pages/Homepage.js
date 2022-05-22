import * as React from 'react';
import BookList from '../components/booklist';
import { GenreSelect } from '../components/genreSelect';
import ApiService from "../services/ApiService";
import { USER_RECOMMENDATIONS } from "../services/Routes";

const Homepage = () => {

  const [books, setBooks] = React.useState([]);

  React.useEffect(() => { getBooks(); }, []);

  const getBooks = (genreId) => {
    ApiService.getAPI(USER_RECOMMENDATIONS(genreId)).then(result => {
      setBooks(result.data);
      console.log(result.data)
    });
  };
  

  return (
    <div style={{padding: '20px'}}>
      <GenreSelect onGenreChange={(genreId) => getBooks(genreId)}/>
      <BookList books={books}/>
    </div>
  )
};

export default Homepage;