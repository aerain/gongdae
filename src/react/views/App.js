import React from 'react';
import '../css/App.css';
import Home from './home/Home';
import { BrowserRouter, Route} from 'react-router-dom'
import Header from './components/Header'
import Submit from './home/Submit';
function App() {
  return (
    <BrowserRouter>
      <div className="content">
        <Route path="/" exact component={Home} />
        <Route path="/submit/:id" component={Submit} />
      </div>
    </BrowserRouter>
  );
}

export default App;
