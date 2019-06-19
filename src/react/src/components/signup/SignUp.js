import React, { Component } from 'react';
import '../../../css/SignUp.css';
import Header from "../Header";
export default class SignUp extends Component {

    constructor(props) {
        super(props);

        this.state = {
            email: "",
            password: "",
            username: "",
            type: 0,
            isSignup: false,
            companyDescription: "",
        }
    }

    signUpToServer = async () => {
        const { state, email, password, username, type, companyDescription } = this.state;
        try {
            let res = await fetch('/api/user', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Charset': 'UTF-8'
                },
                body: (this.state.type === 1)
                    ? (JSON.stringify({member: {email, password, username, type}, companyDescription}))
                    : (JSON.stringify({member: {email, password, username, type}}))
            });
            if(res.ok) this.setState({isSignup: true})
        } catch(err) {
            console.error(err);
        }


    }

    _renderTypeRadio = (value, text) => (
        <label>
            <input type="radio" name="signup-type" value={value} onChange={this.changeType} checked = {this.state.type === value} />
            {text}
        </label>
    )

    _renderForm = () => (
        <div className="signup-form">
            <label htmlFor="email">이메일</label>
            <input type="email" id="email" placeholder="이메일을 입력하세요." onChange={this.changeEmail}/>
            <label htmlFor="password">패스워드</label>
            <input type="password" id="password" placeholder="비밀번호를 입력하세요." onChange={this.changePassword}/>
            <label htmlFor="username">이름</label>
            <input type="text" id="username" placeholder="이름을 입력하세요." onChange={this.changeUsername}/>
            {this._renderCompanyDetail()}
            <div className="type-row">
                {this._renderTypeRadio(0, "일반 사용자")}
                {this._renderTypeRadio(1, "회사")}
            </div>
            <button className="signup-submit" onClick={this.signUpToServer}>오 님들 어서오셈</button>
        </div>
    )

    _renderCompanyDetail = () => {
        if(this.state.type === 1) {
            return (
                <div className="company-description">
                    <label htmlFor="description-content">회사 소개</label>
                    <textarea id="description-content" placeholder="회사를 소개해 주세요." onChange={this.changeCompanyDescription} />
                </div>
            )
        } else return null;
    }

    changeCompanyDescription = e => {
        let companyDescription = e.target.value;
        this.setState({companyDescription})
    }

    render() {
        return(
            <div className="signup-content">
                <Header
                    icon="arrow_back_ios"
                    onClick={this.goBack}
                />
                {this._renderForm()}
            </div>
        )
    }

    goBack = () => this.props.history.goBack();

    changeEmail = e => {
        let email = e.currentTarget.value;
        this.setState({email});
    }
    changePassword = e => {
        let password = e.target.value;
        this.setState({password});
    }
    changeUsername = e => {
        let username = e.target.value;
        this.setState({username});
    }
    changeType = e => {
        let type = parseInt(e.target.value);
        this.setState({type});
    }
}