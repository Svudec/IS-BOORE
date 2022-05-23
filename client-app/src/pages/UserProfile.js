import { Accordion, AccordionDetails, AccordionSummary, Card, CardContent, Grid, Typography } from "@mui/material";
import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import ApiService from "../services/ApiService";
import { USER_PROFILE } from "../services/Routes";
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import BookList from "../components/booklist";
import ReviewList from "../components/reviewList";
import { ReviewForm } from "../components/reviewForm";

export const UserProfile = () => {

    const [user, setUser] = useState(null);
    const [reviewForForm, setReviewForForm] = useState(null);
    const { userId } = useParams();
    const navigate = useNavigate();

    useEffect(() => {
        ApiService.getAPI(USER_PROFILE(userId)).then(res => { setUser(res.data); console.log(res.data) });
    }, [userId]);

    const handleBookDetails = (bookId) => navigate('/book/' + bookId);

    const handleBookRemovePast = (bookId) => {
        user.hasRead = user.hasRead?.filter(book => book.id !== bookId);
        setUser({ ...user });
    }
    const handleBookRemoveFuture = (bookId) => {
        user.wantsToRead = user.wantsToRead?.filter(book => book.id !== bookId);
        setUser({ ...user });
    }
    const handleBookDone = (bookId) => {
        let bk = user.wantsToRead?.find(b => b.id === bookId);
        user.wantsToRead = user.wantsToRead?.filter(book => book.id !== bookId);
        user.hasRead?.push(bk);
        setUser({ ...user });
    }

    const handleReviewDelete = (bookId) => {
        user.reviews = user?.reviews?.filter(rev => rev.id.idBook !== bookId);
        setUser({ ...user });
    }

    const handleEditingStart = (bookId) => setReviewForForm(user.reviews?.find(rev => rev.id.idBook === bookId))


    return <>
        <Card>
            <CardContent>
                <Grid container justifyContent='space-between'>
                    <Grid item xs={6}>
                        {user && <Typography variant="h5" component="div">
                            {user?.firstName + user?.lastName}
                        </Typography>}
                    </Grid>
                    <Grid item >

                    </Grid>
                </Grid>

                <Typography sx={{ mb: 1.5 }} color="text.secondary">
                    <strong>From</strong> {user?.city}
                </Typography>
                <Grid container>
                    <Grid item xs={3}>
                        <Typography variant="subtitle1" sx={{ mb: 1.5 }} color="text.secondary">
                            <strong>Birth Year: </strong>
                        </Typography>
                        <Typography variant="subtitle1" sx={{ mb: 1.5 }} color="text.secondary">
                            <strong>Username: </strong>
                        </Typography>
                        <Typography variant="subtitle1" sx={{ mb: 1.5 }} color="text.secondary">
                            <strong>Email: </strong>
                        </Typography>
                    </Grid>
                    <Grid item xs={9}>
                        <Typography variant="subtitle1" sx={{ mb: 1.5 }} color="text.secondary">
                            {user?.birthYear}
                        </Typography>
                        <Typography variant="subtitle1" sx={{ mb: 1.5 }} color="text.secondary">
                            {user?.username}
                        </Typography>
                        <Typography variant="subtitle1" sx={{ mb: 1.5 }} color="text.secondary">
                            {user?.email}
                        </Typography>
                    </Grid>
                </Grid>

                <Typography variant="body1" mt={3}>
                    {user?.biography}
                </Typography>
            </CardContent>
        </Card>
        <Accordion defaultExpanded>
            <AccordionSummary
                expandIcon={<ExpandMoreIcon />}
                aria-controls="panel1a-content"
                id="panel1a-header"
            >
                <Typography variant="h5">My Books</Typography>
            </AccordionSummary>
            <AccordionDetails sx={{ backgroundColor: 'rgb(231, 235, 240)' }}>
                <Grid container spacing={2}>
                    <Grid item xs={6}>
                        <Typography variant='h6'>Past Readings</Typography>
                        <BookList
                            books={user?.hasRead}
                            onBookClick={handleBookDetails}
                            hasDelete
                            onBookDelete={handleBookRemovePast}
                        />
                    </Grid>
                    <Grid item xs={6}>
                        <Typography variant='h6'>Wish List</Typography>
                        <BookList
                            books={user?.wantsToRead}
                            onBookClick={handleBookDetails}
                            hasDelete
                            onBookDelete={handleBookRemoveFuture}
                            hasDone
                            onBookDone={handleBookDone}
                        />
                    </Grid>
                </Grid>
            </AccordionDetails>
        </Accordion>
        <Accordion onChange={(e, expanded) => setReviewForForm(null)}>
            <AccordionSummary
                expandIcon={<ExpandMoreIcon />}
                aria-controls="panel2a-content"
                id="panel2a-header"
            >
                <Typography variant="h5">{reviewForForm ? 'Review Form' : 'My Reviews'}</Typography>
            </AccordionSummary>
            <AccordionDetails>
                {reviewForForm ?
                    <>
                        <ReviewForm review={reviewForForm} />
                    </>
                    :
                    <ReviewList
                        reviews={user?.reviews}
                        onDelete={handleReviewDelete}
                        onEdit={handleEditingStart}
                    />}
            </AccordionDetails>
        </Accordion>
    </>;
};