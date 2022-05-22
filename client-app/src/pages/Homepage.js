import { Grid, Typography } from '@mui/material';
import * as React from 'react';
import { BookDetails } from '../components/bookDetails';
import BookList from '../components/booklist';
import { GenreSelect } from '../components/genreSelect';
import ApiService from "../services/ApiService";
import { USER_RECOMMENDATIONS } from "../services/Routes";

const Homepage = () => {

  const [books, setBooks] = React.useState([]);
  const [selectedBook, setSelectedBook] = React.useState(null)

  React.useEffect(() => { getBooks(); }, []);

  const getBooks = (genreId) => {
    ApiService.getAPI(USER_RECOMMENDATIONS(genreId)).then(result => {
      setBooks(result.data);
      console.log(result.data)
    });
  };

  const handleSelectedBookChange = (bookId) => {
    console.log(bookId)
    setSelectedBook(books.find(book => book.id === bookId));
  }


  return (
    <div style={{ padding: '20px'}}>
      <Typography variant='h4' gutterBottom component="div">Popular Books</Typography>
      <Grid container justifyContent={'flex-start'}>
        <Grid item xs={2}>
          <GenreSelect onGenreChange={(genreId) => getBooks(genreId)} fullWidth />
        </Grid>
      </Grid>
      <div style={{maxHeight: '600px'}}>
      <Grid container columnGap={3} style={{maxHeight: 'inherit'}}>
        <Grid item xs style={{maxHeight: 'inherit'}}>
          <BookList books={books} onBookClick={handleSelectedBookChange}/>
        </Grid>
        {selectedBook &&
          <Grid item xs={6} style={{maxHeight: 'inherit'}}>
            <BookDetails bookId={selectedBook.id}/>
          </Grid>
        }
      </Grid>
      </div>
    </div>
  )
};

export default Homepage;