// import { useState } from 'react'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import Layout from './Layout';
import HomePage from './HomePage';
import Auth from './Auth';
import SignUp from './SignUp';

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
          </Route>
        </Routes>
      </Router>
    </>
  )
}

export default App