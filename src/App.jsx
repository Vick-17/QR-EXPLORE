// import { useState } from 'react'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import Layout from './Layout';
import HomePage from './pages/HomePage';
import Auth from './pages/Auth';
import SignUp from './pages/SignUp';
import UserProfil from './pages/UserProfil';
import About from './pages/About';
import LegalMentions from './pages/MentionsLegales'
import CookiesPolicy from './pages/Cookies';
import UnderDev from './pages/UnderDev.jsx';

import PlacesAddition from "./pages/PlacesAddition.jsx";
import './Style/App.css';

function App() {


  const userToken = localStorage.getItem('userToken');

  return (
    <Router>
      <Routes>
        <Route path='/' element={<Layout />}>
          <Route index element={<HomePage />} />
          <Route path='/auth' element={<Auth />} />
          <Route path='/signup' element={<SignUp />} />
          <Route path='/places' />
          <Route 
            path='/profile'
            element={userToken ? <UserProfil /> : <Auth />} 
          />
          <Route path='/addition-places' element={<PlacesAddition />} />
          <Route path='/about' element={<About />} />
          <Route path='/mentions-legales' element={<LegalMentions />} />
          <Route path='/cookies' element={<CookiesPolicy />} />
          <Route path='/view-places' element={<UnderDev />} />
          <Route path='/autres-actions' element={<UnderDev />} />

        </Route>
      </Routes>
    </Router>
  )
}

export default App