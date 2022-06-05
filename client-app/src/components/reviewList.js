import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import React from "react";
import { Grid, IconButton } from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';
import ApiService from '../services/ApiService';
import { REVIEW } from '../services/Routes';


function ReviewList(props) {

    const handleDelete = (bookId) => {
        ApiService.deleteAPI(REVIEW(bookId));
        props.onDelete && props.onDelete(bookId);
    }

    const handleEdit = (bookId) => {
        props.onEdit && props.onEdit(bookId);
    }
    
    return (
        <TableContainer component={Paper}>
            <Table sx={{ width: 900 }} aria-label="review table" align="center">
                <TableHead>
                    <TableRow>
                        <TableCell>Title</TableCell>
                        <TableCell>Rating</TableCell>
                        <TableCell></TableCell>
                    </TableRow>
                </TableHead>
                <TableBody data-cy='reviewListTableBody'>
                    {props.reviews?.map((review) => (
                        <TableRow
                            key={review.id.idBook + review.id.idPerson}
                            sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                        >
                            <TableCell component="th" scope="row">{review.book}</TableCell>
                            <TableCell align="left">{review.rating}</TableCell>
                            <TableCell>
                                <Grid container>
                                        {!props.disableEdit && <Grid item xs={6}>
                                            <IconButton aria-label="more details" onClick={() => handleEdit(review.id.idBook)}>
                                                <EditIcon />
                                            </IconButton>
                                        </Grid>}
                                        <Grid item xs={6}>
                                            <IconButton aria-label="more details" onClick={() => handleDelete(review.id.idBook)} data-cy='deleteReviewButton'>
                                                <DeleteIcon />
                                            </IconButton>
                                        </Grid>
                                </Grid>
                            </TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </TableContainer>
    );
}

export default ReviewList;