import { Button, Grid, Link, Paper, TextField, Typography, Select, MenuItem, FormControl, InputLabel } from "@mui/material";
import { useFormik } from "formik";
import { useNavigate } from "react-router-dom";
import AuthService from "../services/AuthService";
import ApiService from "../services/ApiService";
import { CITY_LOV } from "../services/Routes";
import React, { useEffect, useState } from "react";

function Signup() {
    const paperStyle = { padding: 20, width: 280, margin: "20px auto" };
    const btnStyle = { margin: '12px 0' };

    const navigate = useNavigate();

    const [cities, setCities] = useState([]);

    useEffect(() => { getCities(); }, []);

    const getCities = () => {
        ApiService.getAPI(CITY_LOV).then(result => {
            setCities(result.data);
        });
    };
    
    const formik = useFormik({
        initialValues: {
            firstName: '',
            lastname: '',
            birthYear: '',
            username: '',
            password: '',
            email: '',
            biography: '',
            cityId: ''
        },
        onSubmit: (values, formikHelpers) => {
            AuthService.register(values).then(() => navigate('/', { state: { refreshUser: true } }));
        }
    })

    return (
        <Grid>
            <Paper elevation={10} style={paperStyle}>
                <Grid align='center'>
                    <h2>
                        Sign up
                    </h2>
                </Grid>
                <form onSubmit={formik.handleSubmit}>
                    <TextField
                        name='firstName'
                        label='First name'
                        placeholder="Enter first name"
                        value={formik.values.firstName}
                        onChange={formik.handleChange}
                        fullWidth
                        required
                        variant="standard"
                        />
                    <TextField
                        name='lastname'
                        label='Last name'
                        placeholder="Enter last name"
                        value={formik.values.lastname}
                        onChange={formik.handleChange}
                        fullWidth
                        required
                        variant="standard"/>
                    <TextField
                        name="birthYear"
                        label='Birth year'
                        placeholder="Enter birth year"
                        value={formik.values.birthYear}
                        onChange={formik.handleChange}
                        fullWidth
                        required
                        variant="standard"/>
                    <TextField
                        name="username"
                        label='Username'
                        placeholder="Enter username"
                        value={formik.values.username}
                        onChange={formik.handleChange}
                        fullWidth
                        required
                        variant="standard"/>
                    <TextField
                        name="password"
                        label='Password'
                        placeholder="Enter password"
                        value={formik.values.password}
                        onChange={formik.handleChange}
                        type="password"
                        fullWidth
                        required
                        variant="standard"/>
                    <TextField
                        name="email"
                        label='Email'
                        placeholder="Enter email"
                        value={formik.values.email}
                        onChange={formik.handleChange}
                        fullWidth
                        required
                        variant="standard"/>
                    <TextField
                        name="biography"
                        label='Bio'
                        placeholder="Enter bio"
                        value={formik.values.biography}
                        onChange={formik.handleChange}
                        fullWidth
                        required
                        variant="standard"/>
                    <FormControl fullWidth required>
                        <InputLabel>City</InputLabel>
                        <Select
                            name="cityId"
                            value={formik.values.cityId}
                            onChange={formik.handleChange}
                            fullWidth
                            variant="standard"
                        >
                            {cities.map(city => (
                                <MenuItem 
                                    key={city.id}
                                    value={city.id}
                                >
                                    {city.label + ', ' + city.label2}
                                </MenuItem>)
                            )}
                        </Select>
                    </FormControl>
                    <Button
                        type="submit"
                        variant="contained"
                        fullWidth
                        style={btnStyle}>
                        Create account
                    </Button>
                </form>
                <Typography>
                    Already a member?&nbsp;
                    <Link href="/login">
                        Sign in
                    </Link>
                </Typography>
            </Paper>
        </Grid>
    );
}

export default Signup;