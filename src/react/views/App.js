import React from 'react';
import '../css/App.css';
import { BrowserRouter, Switch, Route } from 'react-router-dom'
import { Home, Request, RequestList } from './home'

function App() {
  return (
    <BrowserRouter>
      <Switch>
        <Route path="/" exact component={Home} />
        <Route path="/request" component={Request} />
        <Route path="/request-list/:id" component={RequestList} />
      </Switch>
    </BrowserRouter>
  );
}

export default App;
