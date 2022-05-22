import { Button, Card, CardActions, CardContent, Chip, Grid, Paper, Typography } from "@mui/material";
import { Box } from "@mui/system";
import React, { useEffect, useState } from "react";
import ApiService from "../services/ApiService";
import { BOOK } from "../services/Routes";
import LocalLibraryIcon from '@mui/icons-material/LocalLibrary';

export const BookDetails = (props) => {

    const [book, setBook] = useState({});

    useEffect(() => {
        if (props.bookId) {
            ApiService.getAPI(BOOK(props.bookId)).then(res => { setBook(res.data); console.log(res) });
        }
    }, [props.bookId]);

    return <>
        <Card>
            <CardContent>
                <Typography sx={{ fontSize: 16 }} color="text.secondary" gutterBottom>
                    Book Details
                </Typography>
                <Typography variant="h5" component="div">
                    {book.title}
                </Typography>
                <Typography sx={{ mb: 1.5 }} color="text.secondary">
                    <strong>By</strong> {book?.authors?.map(a => a.firstName).join(', ')}
                </Typography>
                <Grid container>
                    <Grid item xs={3}>
                        <Typography variant="subtitle1" sx={{ mb: 1.5 }} color="text.secondary">
                            <strong>Language: </strong>
                        </Typography>
                        {book?.yearOfIssue && <Typography variant="subtitle1" sx={{ mb: 1.5 }} color="text.secondary">
                            <strong>Published in: </strong>
                        </Typography>}
                        <Typography variant="subtitle1" sx={{ mb: 1.5 }} color="text.secondary">
                            <strong>Rating: </strong>
                        </Typography>
                        <Typography variant="subtitle1" sx={{ mb: 1.5 }} color="text.secondary">
                            <strong>Sold in: </strong>
                        </Typography>
                    </Grid>
                    <Grid item xs={9}>
                        <Typography variant="subtitle1" sx={{ mb: 1.5 }} color="text.secondary">
                            {book?.language}
                        </Typography>
                        {book?.yearOfIssue && <Typography variant="subtitle1" sx={{ mb: 1.5 }} color="text.secondary">
                            {book?.yearOfIssue}
                        </Typography>}
                        <Typography variant="subtitle1" sx={{ mb: 1.5 }} color="text.secondary">
                            {book?.rating}
                        </Typography>
                        <Grid container columnGap={3}>
                            {book?.soldInBookstores?.map(store => <Chip key={store.id} icon={<LocalLibraryIcon />} label={store.name} />)}
                        </Grid>
                    </Grid>
                </Grid>

                <Typography variant="body1" mt={3}>
                    {book.description}
                </Typography>
            </CardContent>
            <CardActions sx={{justifyContent: 'center' }}>
                <Button>Learn More</Button>
            </CardActions>
        </Card>
    </>
}