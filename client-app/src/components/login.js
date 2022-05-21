import { Avatar, Button, FormControl, Grid, Link, Paper, Typography } from "@mui/material";
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import { useNavigate } from "react-router-dom";
import React, { useState } from "react";
import AuthService from "../services/AuthService";
import { Field, Form, Formik } from "formik";
import { TextField } from "formik-mui";


function Login(props) {
    const paperStyle = { padding: 20, width: 280, margin: "20px auto" }
    const avatarStyle = { backgroundColor: '#16a6a1' }
    const btnStyle = { margin: '12px 0' }

    const navigate = useNavigate();

    const handleLogin = (values, formikHelpers) => {
        console.log(values)
        AuthService.login(values).then(() => navigate('/', { state: { refreshUser: true } }));
    };

    return (
        <Grid>
            <Paper elevation={10} style={paperStyle}>
                <Grid align='center'>
                    <Avatar
                        style={avatarStyle}>
                        <LockOutlinedIcon />
                    </Avatar>
                    <h2>
                        Sign in
                    </h2>
                </Grid>
                <Formik
                    initialValues={{
                        username: '',
                        password: ''
                    }}
                    onSubmit={handleLogin}
                >
                    {({ submitForm, isSubmitting }) => (
                        <Form>
                            <Field
                                type="text"
                                name="username"
                                component={TextField}
                                label='Username'
                                placeholder="Enter username"
                                fullWidth
                                required
                                variant="standard"
                            />
                            <Field
                                type="password"
                                label="Password"
                                name="password"
                                component={TextField}
                                placeholder="Enter password"
                                fullWidth
                                required
                                variant="standard"
                            />
                            <Button
                                variant="contained"
                                fullWidth
                                style={btnStyle}
                                onClick={submitForm}>
                                SIGN IN
                            </Button>
                            <Typography>
                                Not a member?&nbsp;
                                <Link href="/register">
                                    Sign up
                                </Link>
                            </Typography>
                        </Form>
                    )}
                </Formik>
            </Paper>
        </Grid>
    );
}

export default Login;