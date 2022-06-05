import { Divider, Grid, IconButton, Typography } from '@mui/material';
import * as React from 'react';
import CheckIcon from '@mui/icons-material/Check';
import CancelIcon from '@mui/icons-material/Cancel';

const Moderator = () => {

    const [reviews, setReviews] = React.useState([]);

    React.useEffect(() => { getReviewsForModeration(); }, []);

    const getReviewsForModeration = () => {
        // ApiService.getAPI(USER_RECOMMENDATIONS(genreId)).then(result => {
        //     setReviews(result.data);
        // });
        setReviews([
            {
                "id": {
                    "idBook": 8,
                    "idPerson": 53
                },
                "book": "It",
                "person": "Jarrod Rodriguez",
                "text": "vulputate, nisi sem semper erat, in consectetuer ipsum nunc id enim. Curabitur massa. Vestibulum accumsan neque et nunc. Quisque ornare tortor at risus. Nunc ac sem ut dolor dapibus gravida. Aliquam tincidunt, nunc ac mattis ornare, lectus ante dictum mi, ac mattis velit justo nec ante. Maecenas mi felis, adipiscing fringilla, porttitor vulputate, posuere vulputate, lacus. Cras interdum. Nunc sollicitudin commodo ipsum. Suspendisse non leo. Vivamus nibh dolor, nonummy ac, feugiat non, lobortis quis, pede. Suspendisse dui. Fusce diam nunc, ullamcorper eu, euismod ac, fermentum vel, mauris. Integer sem elit, pharetra ut, pharetra",
                "rating": 3
            },
            {
                "id": {
                    "idBook": 8,
                    "idPerson": 51
                },
                "book": "It",
                "person": "Vielka Deleon",
                "text": "Donec porttitor tellus non magna. Nam ligula elit, pretium et, rutrum non, hendrerit id, ante. Nunc mauris sapien, cursus in, hendrerit consectetuer, cursus et, magna. Praesent interdum ligula eu enim. Etiam imperdiet dictum magna. Ut tincidunt orci quis lectus. Nullam suscipit, est ac facilisis facilisis, magna tellus faucibus leo, in lobortis tellus justo sit amet nulla. Donec non justo. Proin non massa non ante bibendum ullamcorper. Duis cursus, diam at pretium aliquet, metus urna convallis erat, eget tincidunt dui augue eu tellus. Phasellus elit pede, malesuada vel, venenatis vel, faucibus id, libero. Donec consectetuer",
                "rating": 3
            },
            {
                "id": {
                    "idBook": 8,
                    "idPerson": 56
                },
                "book": "It",
                "person": "Karlo Sudec",
                "text": "Novi",
                "rating": 5
            },
            {
                "id": {
                    "idBook": 8,
                    "idPerson": 37
                },
                "book": "It",
                "person": "Nora Rivera",
                "text": "ligula consectetuer rhoncus. Nullam velit dui, semper et, lacinia vitae, sodales at, velit. Pellentesque ultricies dignissim lacus. Aliquam rutrum lorem ac risus. Morbi metus. Vivamus euismod urna. Nullam lobortis quam a felis ullamcorper viverra. Maecenas iaculis aliquet diam. Sed diam lorem, auctor quis, tristique ac, eleifend vitae, erat. Vivamus nisi. Mauris nulla. Integer urna. Vivamus molestie dapibus ligula. Aliquam erat volutpat.",
                "rating": 2
            },
            {
                "id": {
                    "idBook": 8,
                    "idPerson": 30
                },
                "book": "It",
                "person": "Reece Blankenship",
                "text": "sed, sapien. Nunc pulvinar arcu et pede. Nunc sed orci lobortis augue scelerisque mollis. Phasellus libero mauris, aliquam eu, accumsan sed, facilisis vitae, orci. Phasellus dapibus quam quis diam. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Fusce aliquet magna a neque. Nullam ut",
                "rating": 2
            },
            {
                "id": {
                    "idBook": 8,
                    "idPerson": 35
                },
                "book": "It",
                "person": "Leonard Mercado",
                "text": "nisi magna sed dui. Fusce aliquam, enim nec tempus scelerisque, lorem ipsum sodales purus,",
                "rating": 3
            }
        ]);
    };

    return (
        <div style={{ padding: '20px' }}>
            {reviews?.map(review => <div key={review.id.idPerson}>
                <Grid container>
                    <Grid item xs={2}>
                        <Typography variant="subtitle1" sx={{ mb: 1.5 }} color="text.secondary">
                            <strong>User: </strong>
                        </Typography>
                        <Typography variant="subtitle1" sx={{ mb: 1.5 }} color="text.secondary">
                            <strong>Book: </strong>
                        </Typography>
                        <Typography variant="subtitle1" sx={{ mb: 1.5 }} color="text.secondary">
                            <strong>Rating: </strong>
                        </Typography>
                    </Grid>
                    <Grid item xs={10}>
                        <Typography variant="subtitle1" sx={{ mb: 1.5 }} color="text.secondary">
                            {review.person}
                        </Typography>
                        <Typography variant="subtitle1" sx={{ mb: 1.5 }} color="text.secondary">
                            {review.book}
                        </Typography>
                        <Typography variant="subtitle1" sx={{ mb: 1.5 }} color="text.secondary">
                            {review.rating}
                        </Typography>
                    </Grid>
                </Grid>

                <Typography variant="body1" mt={3}>
                    {review.text}
                </Typography>
                <Grid container justifyContent='flex-end' style={{ gap: '40px' }}>
                    <Grid item>
                        <IconButton aria-label="more details" onClick={() => {}} size='large'>
                            <CheckIcon htmlColor='green'/>
                        </IconButton>
                    </Grid>
                    <Grid item>
                        <IconButton aria-label="more details" onClick={() => {}} size='large'>
                            <CancelIcon htmlColor='red'/>
                        </IconButton>
                    </Grid>
                </Grid>
                <Divider sx={{ marginTop: 5, marginBottom: 5 }} />
            </div>
            )}
        </div>
    )
};

export default Moderator;