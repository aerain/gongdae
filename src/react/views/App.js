import React from 'react';
import '../css/App.css';
import { BrowserRouter, Route} from 'react-router-dom'
import { Home, Request } from './home'

function App() {
  return (
    <BrowserRouter>
      <div className="content">
        <Route path="/" exact component={Home} />
        <Route path="/request/:id" component={Request} />
      </div>
    </BrowserRouter>
  );
}

export default App;
