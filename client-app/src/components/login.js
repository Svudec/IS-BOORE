import { Avatar, Button, Grid, Link, Paper, TextField, Typography } from "@mui/material";
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';

function Login() {
    const paperStyle = { padding: 20, width: 280, margin: "20px auto" }
    const avatarStyle = { backgroundColor: '#16a6a1' }
    const btnStyle = { margin: '12px 0' }

    return (
        <Grid>
            <Paper elevation={10} style={paperStyle}>
                <Grid align='center'>
                    <Avatar style={avatarStyle}><LockOutlinedIcon /></Avatar>
                    <h2>Sign in</h2>
                </Grid>
                <TextField label='Username' placeholder="Enter username" fullWidth required variant="standard" />
                <TextField label='Password' placeholder="Enter password" type="password" fullWidth required variant="standard" />
                <Button variant="contained" fullWidth style={btnStyle}>SIGN IN</Button>
                <Typography> 
                    Not a member?&nbsp;
                    <Link>
                        Sign up
                    </Link>
                </Typography>
            </Paper>
        </Grid>
    );
}

export default Login;