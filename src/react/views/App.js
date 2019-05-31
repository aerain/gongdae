import React from 'react';
import '../css/App.css';
import { BrowserRouter, Route} from 'react-router-dom'
import { Home, Request, RequestList } from './home'

function App() {
  return (
    <BrowserRouter>
      <div className="content">
        <Route path="/" exact component={Home} />
        <Route path="/request" component={Request} />
        <Route path="/request-list/:id" component={RequestList} />
      </div>
    </BrowserRouter>
  );
}

export default App;
