import React, { useState } from "react";
import { Button, Rating, Grid, Paper } from "@mui/material";
import { Field, Form, Formik } from "formik";
import { TextField } from "formik-mui";


export const ReviewForm = (props) => {

    const paperStyle = { padding: 20, width: 280, margin: "20px auto" }
    const btnStyle = { margin: '12px 0' }
    const [rating, setRating] = React.useState(0);

    const handleChange = (event) => {
        let newRating = event.target.value;
        setRating(newRating);
    }

    const handleSubmitReview = (values, formikHelpers) => {
        console.log(values);
    };

    return (
        <Grid>
            <Paper elevation={10} style={paperStyle}>
                <Grid align='center'>
                    <h2>
                        Write your review
                    </h2>
                </Grid>
                <Formik
                    initialValues={{
                        text: '',
                        rating: 0
                    }}
                    onSubmit={handleSubmitReview}
                >
                    {({ submitForm }) => (
                        <Form>
                            <Rating
                                value={rating}
                                onChange={handleChange}
                                name="rating"
                                precision={1}
                                required
                            />
                            <Field
                                type="text"
                                name="text"
                                component={TextField}
                                label='Text'
                                placeholder="Enter text"
                                fullWidth
                                required
                                multiline
                                rows={4}
                                variant="standard"
                            />
                            <Button
                                variant="contained"
                                fullWidth
                                style={btnStyle}
                                onClick={submitForm}>
                                SUBMIT
                            </Button>
                        </Form>
                    )}
                </Formik>
            </Paper>
        </Grid>
    );
};