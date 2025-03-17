import QrCodeIcon from '@mui/icons-material/QrCode';

import React from "react";
import { AppBar, Toolbar, Menu, MenuItem, IconButton, Button } from "@mui/material";
import MenuIcon from "@mui/icons-material/Menu";
import AccountCircleIcon from "@mui/icons-material/AccountCircle";

import './Header.css';

const Header = () => {
  const [anchorEl, setAnchorEl] = React.useState(null);
  const [openMenu, setOpenMenu] = React.useState(null);

  const handleClick = (event, menu) => {
    setAnchorEl(event.currentTarget);
    setOpenMenu(menu);
  };

  const handleClose = () => {
    setAnchorEl(null);
    setOpenMenu(null);
  };

  return (
    <header>
        <AppBar position="static" color="grey">
        <Toolbar className='toolbar'>
            {/* Icône à gauche */}
            <div style={{ flexGrow: 1 }} />
            <IconButton color="inherit">
                <QrCodeIcon />
            </IconButton>

            {/* Dropdown 1 */}
            <Button color="inherit" onClick={(e) => handleClick(e, "menu1")}>Home</Button>

            {/* Dropdown 3 */}
            <Button color="inherit" onClick={(e) => handleClick(e, "menu3")}>Menu</Button>
            <Menu anchorEl={anchorEl} open={openMenu === "menu3"} onClose={handleClose}>
            <MenuItem onClick={handleClose}>Item X</MenuItem>
            <MenuItem onClick={handleClose}>Item Y</MenuItem>
            </Menu>

            {/* Icône à droite */}
            <IconButton color="inherit">
                <AccountCircleIcon />
            </IconButton>
        </Toolbar>
        </AppBar>
    </header>
  );
};

export default Header;
