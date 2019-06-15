import React from 'react';
import '../css/App.css';
import { BrowserRouter, Switch, Route } from 'react-router-dom'
import {Home, ReverseRequest, Request, RequestDetail, Login} from './home'

function App() {
  return (
      <BrowserRouter>
          <Switch>
              <Route exact path="/" exact component={Home} />
              <Route path="/login" component={Login} />
              <Route path="/request/" component={Request} />
              <Route path="/list/:id" component={RequestDetail} />
              <Route path="/reverse/:id" component={ReverseRequest} />
          </Switch>
      </BrowserRouter>
  );
}

export default App;
