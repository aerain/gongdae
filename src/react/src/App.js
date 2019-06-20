import React, {Component} from 'react';
import '../css/App.css';
import { BrowserRouter, Switch, Route, Redirect } from 'react-router-dom'
import {Request, RequestDetail, ReverseRequest, Home} from './components/home'
import { Login } from './components/login';
import Main from "./components/Main";
import { SignUp } from "./components/signup";
import {CompanyForSale, CompanyRequest, CompanyReverseRequest, CompanyReverseList, CompanyReverseDetail } from "./components/company";

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
                <Route exact path="/company" component={CompanyForSale} />
                    <Route path="/company/list/:id" component={CompanyRequest} />
                    <Route path="/company/request/:id" component={CompanyReverseRequest} />
                    <Route exact path="/company/reverse" component={CompanyReverseList}/>
                        <Route path="/company/reverse/:id" component={CompanyReverseDetail}/>
          </Switch>
      </BrowserRouter>
  );
}

export default App;
