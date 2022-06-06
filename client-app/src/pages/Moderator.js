import { Button, Divider, Grid, IconButton, Typography } from '@mui/material';
import * as React from 'react';
import CheckIcon from '@mui/icons-material/Check';
import CancelIcon from '@mui/icons-material/Cancel';
import AssignmentReturnedIcon from '@mui/icons-material/AssignmentReturned';
import axios from 'axios';
import { CAMUNDA_COMPLETE_TASK, CAMUNDA_GET_TASK_FOR_PROCESS, CAMUNDA_GET_VARIABLES, COMPLETE_REVIEW_ASSESSMENT, GET_ACTIVE_PROCESSES } from '../services/Routes';

const Moderator = () => {

    const [reviews, setReviews] = React.useState([]);

    React.useEffect(() => { getReviewsForModeration(); }, []);

    const headers = {
        'Access-Control-Allow-Origin': '*',
        'Content-Type': 'application/json',
        'Access-Control-Allow-Methods': 'GET, PUT, POST, DELETE, OPTIONS'
    };

    const getReviewsForModeration = async () => {

        let revs = [];

        const res = await axios.get(GET_ACTIVE_PROCESSES, { headers: headers });
        console.log(res);
        setReviews(res.data.map(procs => { return { id: procs.id } }));
        console.log(res.data.map(procs => { return { id: procs.id } }))

    };

    const handleTake = async (procId) => {
        let itemIndx = reviews.findIndex(proc => proc.id === procId);

        let res = await axios.get(CAMUNDA_GET_VARIABLES(procId), { headers: headers })

        console.log(res)
        reviews[itemIndx].person = res.data.person.value;
        reviews[itemIndx].book = res.data.book.value;
        reviews[itemIndx].personName = res.data.personName.value;
        reviews[itemIndx].bookTitle = res.data.bookTitle.value;
        reviews[itemIndx].text = res.data.text.value;
        reviews[itemIndx].rating = res.data.rating.value;
        reviews[itemIndx].status = 'TAKEN';

        let resl = await axios.get(CAMUNDA_GET_TASK_FOR_PROCESS(procId), { headers: headers })
        reviews[itemIndx].taskId = resl.data[0].id;
        console.log(resl.data[0].id)
        setReviews([...reviews]);

        axios.post(CAMUNDA_COMPLETE_TASK(resl.data[0].id), {
            variables: {
                moderator: { value: reviews[itemIndx].person },
                status: {value: "TAKEN"}
            }
        }, { headers: headers });
    }

    const handleDecision = (processId, appropriate) => {

        let itemIndx = reviews.findIndex(proc => proc.id === processId);

        axios.post(COMPLETE_REVIEW_ASSESSMENT, {
            "processVariables": {
                "appropriate": { "value": appropriate }
            },
            "messageName": "moderatorAssessedMessage",
            "processInstanceId": processId
        }, { headers: headers }).then(() => {
            reviews.splice(itemIndx, 1);
            setReviews([...reviews])
        })
    }

    return (
        <div style={{ padding: '20px' }}>
            {reviews?.map(review => <div key={review.id}>
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
                            {review.personName}
                        </Typography>
                        <Typography variant="subtitle1" sx={{ mb: 1.5 }} color="text.secondary">
                            {review.bookTitle}
                        </Typography>
                        <Typography variant="subtitle1" sx={{ mb: 1.5 }} color="text.secondary">
                            {review.rating}
                        </Typography>
                    </Grid>
                </Grid>

                {review.status === 'TAKEN' && <Typography variant="body1" mt={3}>
                    {review.text}
                </Typography>}
                <Grid container justifyContent={review.status === 'AVAILABLE' ? 'flex-start' : 'flex-end'} style={{ gap: '40px', marginTop: '10px' }}>
                    {review.status === 'TAKEN' && <>
                        <Grid item>
                            <IconButton aria-label="more details" onClick={() => {handleDecision(review.id, true)}} size='large'>
                                <CheckIcon htmlColor='green' />
                            </IconButton>
                        </Grid>
                        <Grid item>
                            <IconButton aria-label="more details" onClick={() => {handleDecision(review.id, false) }} size='large'>
                                <CancelIcon htmlColor='red' />
                            </IconButton>
                        </Grid>
                    </>}
                    {review.status !== 'TAKEN' && <Grid item>
                        <Button variant="contained" endIcon={<AssignmentReturnedIcon />} onClick={() => handleTake(review.id)}>
                            Take
                        </Button>
                    </Grid>}
                </Grid>
                <Divider sx={{ marginTop: 1, marginBottom: 5 }} />
            </div>
            )}
        </div>
    )
};

export default Moderator;