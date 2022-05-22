import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import React, { useEffect, useState } from "react";

function BookList(props) {
    
    return (
        <TableContainer component={Paper}>
            <Table sx={{ width: 900 }} aria-label="book table" align="center">
                <TableHead>
                    <TableRow>
                        <TableCell>Title</TableCell>
                        <TableCell>Author</TableCell>
                        <TableCell>Year published</TableCell>
                        <TableCell>Rating</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {props.books.map((book) => (
                        <TableRow
                            key={book.id}
                            sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                        >
                            <TableCell component="th" scope="row">{book.title}</TableCell>
                            <TableCell align="left">
                                {book.authors.map(autor => autor.firstName).join(', ')}
                            </TableCell>
                            <TableCell align="left">{book.yearOfIssue}</TableCell>
                            <TableCell align="left">{book.rating.toFixed(2)}</TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </TableContainer>
    );
}

export default BookList;