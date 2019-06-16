import React, {Component} from 'react';
import '../css/App.css';
import { BrowserRouter, Switch, Route, Redirect } from 'react-router-dom'
import {Request, RequestDetail, ReverseRequest, Home} from './home'

function App() {
  return (
      <BrowserRouter>
          <Switch>
              <Route exact path="/" component={Main} />

              <Route exact path="/user" exact component={Home} />
                <Route path="/user/request" component={Request} />
                <Route path="/user/list/:id" component={RequestDetail} />
                <Route path="/user/reverse/:id" component={ReverseRequest} />
          </Switch>
      </BrowserRouter>
  );
}

class Main extends Component {
    render() {
        return (<div>hi</div>)
    }
}

export default App;
