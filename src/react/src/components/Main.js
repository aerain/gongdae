import React, { Component } from 'react';
import '../../css/Main.css'
import { Link } from 'react-router-dom';
export default class Main extends Component {
    render() {
        return(
            <div className="main-content">
                <div className="title">
                    <p className="title-row"><span className="title-accent">공</span>사</p>
                    <p className="title-row"><span className="title-accent">대</span>신 해드립니다.</p>
                </div>
                <div className="btn-container">
                    <Link to="/login" className="btn">로그인하기</Link>
                    <Link to="/signup" className="btn">회원가입</Link>
                </div>
            </div>
        )
    }
    _onLoading = () => (<div>로딩좀합시다</div>)
}