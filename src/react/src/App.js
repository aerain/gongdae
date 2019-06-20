import React from 'react';
import '../css/App.css';
import { BrowserRouter, Switch, Route} from 'react-router-dom'
import {Request, RequestDetail, ReverseRequest, Home, DoneRequest} from './components/home'
import { Login } from './components/login';
import Main from "./components/Main";
import { SignUp } from "./components/signup";
import {CompanyForSale, CompanyRequest, CompanyReverseRequest, CompanyReverseList, CompanyReverseDetail, CompanyDoneRequest } from "./components/company";

export default class App extends React.Component {
    render() {
        return (
            <BrowserRouter>
                <Switch>
                    <Route exact path="/" component={Main}/>
                    <Route exact path="/login" component={Login}/>
                    <Route exact path="/signup" component={SignUp}/>
                    <Route exact path="/user" component={Home}/>
                        <Route path="/user/done" component={DoneRequest}/>
                        <Route path="/user/request" component={Request}/>
                        <Route path="/user/list/:id" component={RequestDetail}/>
                        <Route path="/user/reverse/:id" component={ReverseRequest}/>
                    <Route exact path="/company" component={CompanyForSale}/>
                        <Route path="/company/done" component={CompanyDoneRequest}/>
                        <Route path="/company/list/:id" component={CompanyRequest}/>
                        <Route path="/company/request/:id" component={CompanyReverseRequest}/>
                        <Route exact path="/company/reverse" component={CompanyReverseList}/>
                            <Route path="/company/reverse/:id" component={CompanyReverseDetail}/>
                </Switch>
            </BrowserRouter>
        );
    }
}
