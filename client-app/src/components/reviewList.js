import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import React from "react";

function ReviewList(props) {
    
    return (
        <TableContainer component={Paper}>
            <Table sx={{ width: 900 }} aria-label="review table" align="center">
                <TableHead>
                    <TableRow>
                        <TableCell>Title</TableCell>
                        <TableCell>User</TableCell>
                        <TableCell>Rating</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {props.reviews.map((review) => (
                        <TableRow
                            key={review.id.idBook + review.id.idPerson}
                            sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                        >
                            <TableCell component="th" scope="row">{review.book}</TableCell>
                            <TableCell align="left">{review.person}</TableCell>
                            <TableCell align="left">{review.rating}</TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </TableContainer>
    );
}

export default ReviewList;