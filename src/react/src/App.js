import React, {Component} from 'react';
import '../css/App.css';
import { BrowserRouter, Switch, Route, Redirect } from 'react-router-dom'
import {Request, RequestDetail, ReverseRequest, Home} from './components/home'
import { Login } from './components/login';
import Main from "./components/Main";
import { SignUp } from "./components/signup";

function App() {
  return (
      <BrowserRouter>
          <Switch>
                <Route exact path="/" component={Main} />
                <Route exact path="/login" component={Login} />
                <Route exact path="/signup" component={SignUp} />
                <Route exact path="/user" exact component={Home} />
                    <Route path="/user/request" component={Request} />
                    <Route path="/user/list/:id" component={RequestDetail} />
                    <Route path="/user/reverse/:id" component={ReverseRequest} />
          </Switch>
      </BrowserRouter>
  );
}

export default App;
