import { Button, Grid, Link, Paper, TextField, Typography } from "@mui/material";
import ApiService from "../services/ApiService";
import { CITY_LOV } from "../services/Routes";

function Signup() {
    const paperStyle = { padding: 20, width: 280, margin: "20px auto" }
    const btnStyle = { margin: '12px 0' }

    //useEffect sa praznom listom []
    //ApiService.getAPI(CITY_LOV).then(result => setCities(result))
    //state cities -> array [{}]

    return (
        <Grid>
            <Paper elevation={10} style={paperStyle}>
                <Grid align='center'>
                    <h2>
                        Sign up
                    </h2>
                </Grid>
                {cities.map(city => <MenuItem key={city.id} value={city.id}>{city.label + ', ' + city.label2}</MenuItem>)}
                <TextField
                    label='First name'
                    placeholder="Enter first name"
                    fullWidth
                    required
                    variant="standard"
                    />
                <TextField
                    label='Last name'
                    placeholder="Enter last name"
                    fullWidth
                    required
                    variant="standard"/>
                <TextField
                    label='Birth year'
                    placeholder="Enter birth year"
                    fullWidth
                    required
                    variant="standard"/>
                <TextField
                    label='Username'
                    placeholder="Enter username"
                    fullWidth
                    required
                    variant="standard"/>
                <TextField
                    label='Password'
                    placeholder="Enter password"
                    type="password"
                    fullWidth
                    required
                    variant="standard"/>
                <TextField
                    label='Email'
                    placeholder="Enter email"
                    fullWidth
                    required
                    variant="standard"/>
                <TextField
                    label='Bio'
                    placeholder="Enter bio"
                    fullWidth
                    required
                    variant="standard"/>
                <TextField
                    label='Postal code'
                    placeholder="Choose postal code"
                    fullWidth
                    required
                    variant="standard"/>
                <Button
                    variant="contained"
                    fullWidth
                    style={btnStyle}>
                    Create account
                </Button>
                <Typography>
                    Already a member?&nbsp;
                    <Link href="#">
                        Sign in
                    </Link>
                </Typography>
            </Paper>
        </Grid>
    );
}

export default Signup;