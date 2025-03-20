// import { useState } from 'react'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import Layout from './Layout';
import HomePage from './pages/HomePage';
import Auth from './pages/Auth';
import SignUp from './pages/SignUp';

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