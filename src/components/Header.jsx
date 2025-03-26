import QrCodeIcon from '@mui/icons-material/QrCode';
import { IconButton } from "@mui/material";
import AccountCircleIcon from "@mui/icons-material/AccountCircle";
import { Link } from 'react-router-dom';

import 'bootstrap/dist/js/bootstrap.bundle.min';

import "../Style/components/Header.css"


const Header = () => {
  return (
    <header className=''>
        <nav className="navbar navbar-expand-lg ">
            <div className="container-fluid">
                <div className='iconButton items'>
                    <Link to="/" className='navbar-brand'>
                        <IconButton color="white" className='IconButton'>
                            <QrCodeIcon color="white" className='QrCodeIcon'/>
                        </IconButton>
                    </Link>
                </div>
                <div className="collapse navbar-collapse items-button" id="navbarNavAltMarkup">
                    <div className="navbar-nav items">
                        <Link to="/" className='nav-link active items'>Home</Link>
                    </div>
                    <div className="navbar-nav items">
                        <Link to="/about" className='nav-link active items'>About</Link>
                    </div>

                    <div className="dropdown">
                        <button className=" dropdown-toggle items dropdown-button" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Menu
                        </button>
                        <ul className="dropdown-menu items dropdown-button">
                            <li>
                                <Link to="/" className='nav-link active items'>Action</Link>
                            </li>
                            <li>
                                <Link to="/" className='nav-link active items'>Another Action</Link>
                            </li>
                            <li>
                                <Link to="/" className='nav-link active items'>Another Action</Link>
                            </li>
                        </ul>
                    </div>
                </div>
                <div className='iconButton'>
                    <Link to="/auth" className='navbar-brand'>
                        <IconButton color="white" className='IconButton'>
                            <AccountCircleIcon color="white" className='AccountIcon' />
                        </IconButton>
                    </Link>
                </div>
            </div>
        </nav>
    </header>
  );
};

export default Header;
