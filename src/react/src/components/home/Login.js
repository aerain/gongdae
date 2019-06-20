import React, { Component } from 'react';

export default class Login extends Component {
    render() {
        return(
            <div className="login-content" >
                <form action="/login" method="POST">
                    <p>
                        <input type="text" name="username" />
                    </p>
                    <p>
                        <input type="password" name="password" />
                    </p>
                    <input type="submit" value="hi" />
                </form>
            </div>
        )
    }
}