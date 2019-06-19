import React, { Component } from 'react';
import '../../css/Main.css'
import { Link } from 'react-router-dom';
export default class Main extends Component {
    render() {
        return(
            <div className="main-content">
                <div>
                    왜 안돼
                </div>
                <div>
                    개웃기네
                </div>
                <Link to="/login">로그인하기</Link>
                <Link to="/signup">회원가입</Link>
            </div>
        )
    }
}