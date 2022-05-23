import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import React, { useEffect, useState } from "react";
import { Grid, IconButton } from '@mui/material';
import ArrowForwardIosIcon from '@mui/icons-material/ArrowForwardIos';
import ApiService from '../services/ApiService';
import { WISHLIST } from '../services/Routes';
import DeleteIcon from '@mui/icons-material/Delete';
import BeenhereIcon from '@mui/icons-material/Beenhere';

function BookList(props) {

    const handleArrowClick = (bookId) => {
        props.onBookClick && props.onBookClick(bookId);
    }

    const handleBookDelete = (bookId) => {
        ApiService.deleteAPI(WISHLIST(bookId));
        props.onBookDelete && props.onBookDelete(bookId);
    }

    const handleBookDone = (bookId) => {
        ApiService.postAPI(WISHLIST(bookId, true));
        props.onBookDone && props.onBookDone(bookId);
    }


    return (<>
        <TableContainer sx={{ maxHeight: 'inherit' }} component={Paper}>
            <Table aria-label="book table" align="center">
                <TableHead>
                    <TableRow>
                        <TableCell>Title</TableCell>
                        <TableCell>Author</TableCell>
                        {!props.hasDelete && <TableCell>Year published</TableCell>}
                        <TableCell>Rating</TableCell>
                        <TableCell></TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {props.books?.map((book) => (
                        <TableRow
                            key={book.id}
                            sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                        >
                            <TableCell component="th" scope="row">{book.title}</TableCell>
                            <TableCell align="left">
                                {book.authors.map(autor => autor.firstName).join(', ')}
                            </TableCell>
                            {!props.hasDelete && <TableCell align="left">{book.yearOfIssue}</TableCell>}
                            <TableCell align="left">{book.rating.toFixed(2)}</TableCell>
                            <TableCell>
                                <Grid container>
                                    {props.hasDone &&
                                        <Grid item xs={4}>
                                            <IconButton aria-label="more details" onClick={() => handleBookDone(book.id)}>
                                                <BeenhereIcon />
                                            </IconButton>
                                        </Grid>
                                    }
                                    {props.hasDelete &&
                                        <Grid item xs={props.hasDone ? 4 : 6}>
                                            <IconButton aria-label="more details" onClick={() => handleBookDelete(book.id)}>
                                                <DeleteIcon />
                                            </IconButton>
                                        </Grid>
                                    }
                                    <Grid item xs={props.hasDone ? 4 : props.hasDelete ? 6 : true}>
                                        <IconButton aria-label="more details" onClick={() => handleArrowClick(book.id)}>
                                            <ArrowForwardIosIcon />
                                        </IconButton>
                                    </Grid>

                                </Grid>
                            </TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </TableContainer>
    </>
    );
}

export default BookList;