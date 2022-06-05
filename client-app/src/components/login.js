import { Avatar, Button, FormControl, Grid, Link, Paper, TextField, Typography } from "@mui/material";
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import { useNavigate, useOutletContext } from "react-router-dom";
import React, { useState } from "react";
import AuthService from "../services/AuthService";
import { Field, Form, Formik, useFormik } from "formik";


function Login(props) {
    const paperStyle = { padding: 20, width: 280, margin: "auto" }
    const avatarStyle = { backgroundColor: '#16a6a1' }
    const btnStyle = { margin: '12px 0' }

    const navigate = useNavigate();
    const sendErrorNotification = useOutletContext();

    const formik = useFormik({
        initialValues: {
            username: '',
            password: ''
        },
        onSubmit: (values, formikHelpers) => {
            AuthService.login(values)
                .then((res) => res?.role === 'MODERATOR' ? navigate('/moderator', { state: { refreshUser: true } }) : navigate('/', { state: { refreshUser: true } }))
                .catch(e => {
                    if (e.response.status === 401) {
                        sendErrorNotification("Invalid username or password!");
                    } else {
                        sendErrorNotification("Something went wrong!")
                    }
                });
        }
    })

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
                <form onSubmit={formik.handleSubmit}>
                    <TextField
                        type="text"
                        name="username"
                        label='Username'
                        placeholder="Enter username"
                        fullWidth
                        required
                        variant="standard"
                        value={formik.values.username}
                        onChange={formik.handleChange}
                    />
                    <TextField
                        type="password"
                        label="Password"
                        name="password"
                        placeholder="Enter password"
                        fullWidth
                        required
                        variant="standard"
                        value={formik.values.password}
                        onChange={formik.handleChange}
                    />
                    <Button
                        variant="contained"
                        fullWidth
                        style={btnStyle}
                        type='submit' >
                        SIGN IN
                    </Button>
                </form>
                <Typography>
                    Not a member?&nbsp;
                    <Link href="/register">
                        Sign up
                    </Link>
                </Typography>
            </Paper>
        </Grid >
    );
}

export default Login;