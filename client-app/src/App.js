import { Outlet, useLocation, useNavigate } from "react-router-dom";
import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import Menu from '@mui/material/Menu';
import MenuIcon from '@mui/icons-material/Menu';
import Container from '@mui/material/Container';
import Button from '@mui/material/Button';
import MenuItem from '@mui/material/MenuItem';
import AuthService from "./services/AuthService";
import { ReviewForm } from "./components/reviewForm";
import { Alert, Snackbar } from "@mui/material";

function App(props) {

  const [anchorElNav, setAnchorElNav] = React.useState(null);
  const [loggedInUser, setLoggedInUser] = React.useState(null);

  const [snackBarOpened, setSnackBarOpened] = React.useState(false);
  const [snackbarMessage, setSnackbarMessage] = React.useState('');

  const location = useLocation();
  const navigate = useNavigate();

  React.useEffect(() => {
    console.log(location.pathname)
    if (location?.state?.refreshUser) {
      refreshAuthenticatedUser();
    }
  }, [location.pathname]);

  React.useEffect(() => { refreshAuthenticatedUser(); }, []);

  React.useEffect(() => {
    if (snackbarMessage !== '') {
      setSnackBarOpened(true);
    } else {
      setSnackBarOpened(false);
    }
  }, [snackbarMessage]);

  const refreshAuthenticatedUser = () => {
    let user = AuthService.getCurrentUser();
    //console.log(user)

    if (user == null && location.pathname !== '/login' && location.pathname !== '/register') {
      navigate('/login', { replace: true })
    }
    setLoggedInUser(user)
  };

  const pages = loggedInUser?.role === 'USER' ? [{ label: "Home", url: '/' }, { label: "Profile", url: '/user' }] : [];

  const handleLogout = () => {
    AuthService.logout();
    setLoggedInUser(null);
    refreshAuthenticatedUser()
  }

  const handleOpenNavMenu = (event) => {
    setAnchorElNav(event.currentTarget);
  };

  const handleCloseNavMenu = (pageUrl) => {
    setAnchorElNav(null);
    navigate(pageUrl);
  };

  const handleCloseSnackbar = (event, reason) => {
    if (reason === 'clickaway') {
      return;
    }
    setSnackbarMessage('');
  }

  const openErrorSnackbar = (message) => {
    setSnackbarMessage(message);
  }

  return (
    <div className="container" style={{ backgroundColor: 'rgb(231, 235, 240)', minHeight: '100vh', paddingBottom: '30px'}}>
      <Snackbar open={snackBarOpened} autoHideDuration={6000} onClose={handleCloseSnackbar}>
        <Alert onClose={handleCloseSnackbar} severity='error' sx={{ width: '100%' }}>
          {snackbarMessage}
        </Alert>
      </Snackbar>
      {loggedInUser &&
        <AppBar position="static">
          <Container maxWidth="xl">
            <Toolbar disableGutters>
              <Typography
                variant="h6"
                noWrap
                component="a"
                href={loggedInUser?.role === 'MODERATOR' ? '/moderator' : "/"}
                sx={{
                  mr: 2,
                  display: { xs: 'none', md: 'flex' },
                  fontFamily: 'monospace',
                  fontWeight: 700,
                  letterSpacing: '.3rem',
                  color: 'inherit',
                  textDecoration: 'none',
                }}
              >
                Book Reviews
              </Typography>

              <Box sx={{ flexGrow: 1, display: { xs: 'flex', md: 'none' } }}>
                <IconButton
                  size="large"
                  aria-label="account of current user"
                  aria-controls="menu-appbar"
                  aria-haspopup="true"
                  onClick={handleOpenNavMenu}
                  color="inherit"
                >
                  <MenuIcon />
                </IconButton>
                <Menu
                  id="menu-appbar"
                  anchorEl={anchorElNav}
                  anchorOrigin={{
                    vertical: 'bottom',
                    horizontal: 'left',
                  }}
                  keepMounted
                  transformOrigin={{
                    vertical: 'top',
                    horizontal: 'left',
                  }}
                  open={Boolean(anchorElNav)}
                  onClose={handleCloseNavMenu}
                  sx={{
                    display: { xs: 'block', md: 'none' },
                  }}
                >
                  {pages.map((page) => (
                    <MenuItem key={page.url} onClick={() => handleCloseNavMenu(page.url)}>
                      <Typography textAlign="center">{page.label}</Typography>
                    </MenuItem>
                  ))}
                </Menu>
              </Box>
              <Typography
                variant="h5"
                noWrap
                component="a"
                href="/"
                sx={{
                  mr: 2,
                  display: { xs: 'flex', md: 'none' },
                  flexGrow: 1,
                  fontFamily: 'monospace',
                  fontWeight: 700,
                  letterSpacing: '.3rem',
                  color: 'inherit',
                  textDecoration: 'none',
                }}
              >
                Book Reviews
              </Typography>
              <Box sx={{ flexGrow: 1, display: { xs: 'none', md: 'flex' } }}>
                {pages.map((page) => (
                  <Button
                    key={page.url}
                    onClick={() => handleCloseNavMenu(page.url)}
                    sx={{ my: 2, color: 'white', display: 'block' }}
                  >
                    {page.label}
                  </Button>
                ))}
              </Box>

              <Button
                sx={{ p: 0, my: 2, color: 'white', display: 'block' }}
                onClick={handleLogout}
              >
                Sign out
              </Button>
            </Toolbar>
          </Container>
        </AppBar>
      }

      <Outlet context={openErrorSnackbar}/>
    </div>
  );
}

export default App;