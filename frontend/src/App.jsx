import { useState } from 'react'
import { BrowserRouter, Route, Routes } from 'react-router-dom'

import Layout from './components/Layout'
import ListagemVeiculos from './pages/ListagemVeiculos/index';

function App() {

  return (
    <BrowserRouter>
      <Routes>
        <Route element={<Layout />}>
          <Route path={'/'} element={<ListagemVeiculos />} />
        </Route>
      </Routes>
    </BrowserRouter>
  )
}

export default App
