import React, { Component } from 'react';
import '../../../css/Login.css'
export default class Login extends Component {
    render() {
        return (
            <div className="login-content">
                <form method="POST" action="/login" className="login-form">
                    <input type="text" name="username" className="login-input" placeholder="아이디나 이메일을 입력하세요" />
                    <input type="password" name="password" className="login-input" placeholder="비밀번호를 입력하세요" />
                    <input type="submit" value="로그인" className="login-submit"/>
                </form>
            </div>
        )
    }
}