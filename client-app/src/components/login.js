import { Avatar, Button, Grid, Link, Paper, TextField, Typography } from "@mui/material";
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Checkbox from '@mui/material/Checkbox';
import FormControlLabel from '@mui/material/FormControlLabel';


function Login() {
    const paperStyle = { padding: 20, height: '70vh', width: 280, margin: "20px auto" }
    const avatarStyle = { backgroundColor: '#16a6a1' }
    const btnStyle = { margin: '8px 0' }

    return (
        <Grid>
            <Paper elevation={10} style={paperStyle}>
                <Grid align='center'>
                    <Avatar style={avatarStyle}><LockOutlinedIcon /></Avatar>
                    <h2>Sign in</h2>
                </Grid>
                <TextField label='Username' placeholder="Enter username" fullWidth required margin="dense" variant="standard" />
                <TextField label='Password' placeholder="Enter password" type="password" fullWidth required margin="dense" variant="standard" />
                <FormControlLabel
                    control={
                        <Checkbox
                            name="Checked"
                            color="primary"
                        />
                    }
                    label="Remember me"
                />
                <Button variant="contained" fullWidth style={btnStyle}>SIGN IN</Button>
                <Typography> 
                    Not a member?
                    <Link href="#">
                        Sign up
                    </Link>
                </Typography>
            </Paper>
        </Grid>
    );
}

export default Login;