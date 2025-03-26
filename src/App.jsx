// import { useState } from 'react'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import Layout from './Layout';
import HomePage from './pages/HomePage';
import Auth from './pages/Auth';
import SignUp from './pages/SignUp';
import About from './pages/About';
import LegalMentions from './pages/MentionsLegales'
import CookiesPolicy from './pages/Cookies';

import './Style/App.css';

function App() {


  return (
    <>
      <Router>
        <Routes>
          <Route path='/' element={<Layout />}>
            <Route index element={<HomePage />} />
            <Route path='/auth' element={<Auth />} />
            <Route path='/signup' element={<SignUp />} />
            <Route path='/about' element={<About />} />
            <Route path='/mentions-legales' element={<LegalMentions />} />
            <Route path='/cookies' element={<CookiesPolicy />} />
          </Route>
        </Routes>
      </Router>
    </>
  )
}

export default App