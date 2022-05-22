import { Grid, TextField, Typography } from '@mui/material';
import { Box } from '@mui/system';
import * as React from 'react';
import { BookDetails } from '../components/bookDetails';
import BookList from '../components/booklist';
import { GenreSelect } from '../components/genreSelect';
import ApiService from "../services/ApiService";
import { BOOK_SEARCH, USER_RECOMMENDATIONS } from "../services/Routes";
import SearchIcon from '@mui/icons-material/Search';

const Homepage = () => {

  const [books, setBooks] = React.useState([]);
  const [selectedBook, setSelectedBook] = React.useState(null)
  const [searchValue, setSearchValue] = React.useState('')

  React.useEffect(() => { getBooks(); }, []);

  const getBooks = (genreId) => {
    ApiService.getAPI(USER_RECOMMENDATIONS(genreId)).then(result => {
      setBooks(result.data);
    });
  };

  const handleSelectedBookChange = (bookId) => {
    console.log(bookId)
    setSelectedBook(books.find(book => book.id === bookId));
  }

  const searchForBook = () => {
    if(searchValue != ''){
      ApiService.getAPI(BOOK_SEARCH(searchValue)).then(result => {
        setBooks(result.data);
        setSelectedBook(null);
      })
    }
  }

  const handleSearchBarKeyPress = (e) => {
    if(e.keyCode === 13){
      searchForBook();
    }
  }

  const handleGenreChange = (genreId) => {
    getBooks(genreId);
    setSearchValue('')
  }

  return (
    <div style={{ padding: '20px' }}>
      <Typography sx={{ mb: 5 }} variant='h4' gutterBottom component="div">{searchValue != '' ? 'Search Results' : 'Popular Books'}</Typography>
      <Grid container justifyContent={'space-between'}>
        <Grid item xs={2}>
          <GenreSelect onGenreChange={handleGenreChange} fullWidth />
        </Grid>
        <Grid item xs={2}>
          <Box sx={{ display: 'flex', alignItems: 'flex-end' }}>
            <SearchIcon sx={{ color: 'action.active', mr: 1, my: 0.5 }}/>
            <TextField 
            id="input-with-sx" 
            label="Search" 
            variant="standard" 
            value={searchValue} 
            onChange={event => setSearchValue(event.target.value)}
            onKeyDown={handleSearchBarKeyPress}
            />
          </Box>
        </Grid>
      </Grid>
      <div style={{ maxHeight: '600px', marginTop: '20px' }}>
        <Grid container columnGap={3} style={{ maxHeight: 'inherit' }}>
          <Grid item xs style={{ maxHeight: 'inherit' }}>
            <BookList books={books} onBookClick={handleSelectedBookChange} />
          </Grid>
          {selectedBook &&
            <Grid item xs={6} style={{ maxHeight: 'inherit' }}>
              <BookDetails bookId={selectedBook.id} />
            </Grid>
          }
        </Grid>
      </div>
    </div>
  )
};

export default Homepage;