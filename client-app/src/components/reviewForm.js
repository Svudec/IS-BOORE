import React, { useState } from "react";
import { Button, Rating, Grid, Paper, TextField } from "@mui/material";
import { Field, Form, Formik } from "formik";
import ApiService from "../services/ApiService";
import { REVIEW } from "../services/Routes";


export const ReviewForm = (props) => {

    const paperStyle = { padding: 20, width: 280, margin: "20px auto" }
    const btnStyle = { margin: '12px 0' }
    const [rating, setRating] = React.useState(0);
    const [text, setText] = useState('');


    const handleSubmitReview = () => {
        if (rating) {
            let reviewObj = {
                book: props.bookId,
                text: text,
                rating: rating
            };

            ApiService.postAPI(REVIEW(), reviewObj);
            console.log("Review poslan.")
        }
    };

    return (
        <Grid>
            <Paper elevation={10} style={paperStyle}>
                <Grid align='center'>
                    <h2>
                        Write your review
                    </h2>
                </Grid>
                <Rating
                    value={rating}
                    onChange={(e, value) => setRating(value)}
                    precision={1}
                    required
                />
                <TextField
                    type="text"
                    label='Text'
                    placeholder="Enter text"
                    fullWidth
                    required
                    multiline
                    rows={4}
                    variant="standard"
                    value={text}
                    onChange={e => setText(e.target.value)}
                />
                <Button
                    variant="contained"
                    fullWidth
                    style={btnStyle}
                    onClick={handleSubmitReview}>
                    SUBMIT
                </Button>
            </Paper>
        </Grid>
    );
};