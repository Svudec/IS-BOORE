import { Accordion, AccordionDetails, AccordionSummary, Button, Card, CardContent, Chip, Divider, Grid, Typography } from "@mui/material";
import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import ApiService from "../services/ApiService";
import { BOOK, WISHLIST } from "../services/Routes";
import LocalLibraryIcon from '@mui/icons-material/LocalLibrary';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import ReviewList from "../components/reviewList";
import AddIcon from '@mui/icons-material/Add';
import CheckIcon from '@mui/icons-material/Check';

export const BookProfile = () => {

    const [book, setBook] = useState(null);
    const [addedToWishlist, setAddedToWishlist] = useState(false);
    let { bookId } = useParams();

    useEffect(() => {
        if (bookId) {
            ApiService.getAPI(BOOK(bookId)).then(res => { setBook(res.data); console.log(res.data) });
        }
    }, [bookId]);

    const handleAddToWishlist = () => {
        ApiService.postAPI(WISHLIST(bookId, false)).then(res => console.log(res.data))
        setAddedToWishlist(true);
    }

    return <>
        <Card>
            <CardContent>
                <Grid container justifyContent='space-between'>
                    <Grid item xs={6}>
                        <Typography variant="h5" component="div">
                            {book?.title}
                        </Typography>
                    </Grid>
                    <Grid item >
                        {addedToWishlist ?
                            <Button variant="outlined" startIcon={<CheckIcon />} disabled>
                                Added to Wishlist
                            </Button>
                            :
                            <Button variant="outlined" startIcon={<AddIcon />} onClick={handleAddToWishlist}>
                                Add to Wishlist
                            </Button>}
                    </Grid>
                </Grid>

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
                    </Grid>
                </Grid>

                <Typography variant="body1" mt={3}>
                    {book?.description}
                </Typography>
            </CardContent>
        </Card>
        <Accordion>
            <AccordionSummary
                expandIcon={<ExpandMoreIcon />}
                aria-controls="panel1a-content"
                id="panel1a-header"
            >
                <Typography variant="h5">Bookstores</Typography>
            </AccordionSummary>
            <AccordionDetails sx={{ backgroundColor: 'rgb(231, 235, 240)' }}>
                <Grid container justifyContent='space-evenly' rowGap={3}>
                    {book?.soldInBookstores?.map(bookstore =>
                        <Grid item key={bookstore.id}>
                            <Card variant="outlined">
                                <CardContent>
                                    <Typography variant="h5" component="div" gutterBottom>
                                        {bookstore.name}
                                    </Typography>
                                    <Typography variant="body1">
                                        {bookstore.adress}
                                    </Typography>
                                    <Typography variant="body2" gutterBottom>
                                        {bookstore.city}
                                    </Typography>
                                    <Typography variant="body2">
                                        <a href={bookstore.url}>{bookstore.url}</a>
                                    </Typography>
                                </CardContent>
                            </Card>
                        </Grid>
                    )}
                </Grid>
            </AccordionDetails>
        </Accordion>
        <Accordion>
            <AccordionSummary
                expandIcon={<ExpandMoreIcon />}
                aria-controls="panel2a-content"
                id="panel2a-header"
            >
                <Typography variant="h5">Reviews</Typography>
            </AccordionSummary>
            <AccordionDetails>
                {book?.reviews?.map(review => <div key={review.id.idPerson}>
                    <Grid container>
                        <Grid item xs={3}>
                            <Typography variant="subtitle1" sx={{ mb: 1.5 }} color="text.secondary">
                                <strong>User: </strong>
                            </Typography>
                            <Typography variant="subtitle1" sx={{ mb: 1.5 }} color="text.secondary">
                                <strong>Rating: </strong>
                            </Typography>
                        </Grid>
                        <Grid item xs={9}>
                            <Typography variant="subtitle1" sx={{ mb: 1.5 }} color="text.secondary">
                                {review.person}
                            </Typography>
                            <Typography variant="subtitle1" sx={{ mb: 1.5 }} color="text.secondary">
                                {review.rating}
                            </Typography>
                        </Grid>
                    </Grid>

                    <Typography variant="body1" mt={3}>
                        {review.text}
                    </Typography>
                    <Divider sx={{ marginTop: 5, marginBottom: 5 }} />
                </div>
                )}
            </AccordionDetails>
        </Accordion>
    </>;
};