import React, { Component } from 'react';
import { Switch, Route } from 'react-router-dom';
import {Request, RequestDetail, ReverseRequest, Home} from "./index";
function UserSwitch({match}) {
    return (
        <Switch>
            <Route exact path={match.url} component={Home} />
            <Route exact path={`${match.url}/request`} component={Request} />
            <Route exact path={`${match.url}/list/:id`} component={RequestDetail} />
            <Route exact path={`${match.url}/reverse/:id`} component={ReverseRequest} />
        </Switch>
    )
}

export default UserSwitch;
