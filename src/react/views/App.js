import React from 'react';
import '../css/App.css';
import Home from './home/Home';
import { BrowserRouter} from 'react-router-dom'
import Header from './components/Header'
function App() {
  return (
    <BrowserRouter>
      <div className="content" style={{height: window.innerHeight}}>
        <Header />
        <Home />
      </div>
    </BrowserRouter>
  );
}

export default App;
