import React, { useEffect, useState } from "react";
import { Button, Rating, Grid, Paper, TextField } from "@mui/material";
import { Field, Form, Formik } from "formik";
import ApiService from "../services/ApiService";
import { REVIEW, START_REVIEW_ASSESSMENT } from "../services/Routes";
import { BookSelect } from "./bookSelect";
import AuthService from "../services/AuthService";


export const ReviewForm = (props) => {

    const paperStyle = { padding: 20, width: 280, margin: "20px auto" }
    const btnStyle = { margin: '12px 0' }
    const [review, setReview] = useState(null);

    useEffect(() => {
        let user = AuthService.getCurrentUser();
        if (!props.review?.book) {
            setReview({
                book: -1,
                person: user?.id,
                text: '',
                rating: 0
            })
        } else {
            setReview({
                book: props.review.id.idBook,
                text: props.review.text,
                rating: props.review.rating,
                person: user?.id
            })
        }
    }, [props.review])

    const handleChange = (propertieName, value) => {
        let temp = { ...review };
        temp[propertieName] = value;
        setReview(temp);
    }


    const handleSubmitReview = () => {
        if (review.rating) {
            let postData = { variables: {} };
            Object.keys(review).forEach(key => postData.variables.key = { value: review[key] });
            ApiService.postAPI(START_REVIEW_ASSESSMENT, postData).then(() => {
                props.onSave && props.onSave();
            });
        };
    };

    return (
        <Grid>
            <Paper elevation={10} style={paperStyle}>
                <Grid align='center'>
                    <h2>
                        Write your review
                    </h2>
                </Grid>
                <BookSelect
                    disabled={props.review?.id?.idBook ? true : false}
                    onChange={newVal => handleChange('book', newVal)}
                    fullWidth
                    defaultValue={props.review?.id?.idBook}
                />
                <Rating
                    value={review ? review.rating : 0}
                    onChange={(e, value) => handleChange('rating', value)}
                    precision={1}
                    required
                    size="large"
                    sx={{ marginBottom: 3, marginTop: 3 }}
                />
                <TextField
                    name="text"
                    type="text"
                    label='Text'
                    placeholder="Enter text"
                    fullWidth
                    required
                    multiline
                    rows={4}
                    variant="standard"
                    value={review?.text}
                    onChange={e => handleChange('text', e.target.value)}
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