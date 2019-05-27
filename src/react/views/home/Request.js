import React, { Component } from 'react'
import Header from '../components/Header';
import '../../css/Request.css'
export default class Request extends Component {
    
    componentDidMount = () => {
        
    }

    goBack = () => this.props.history.goBack();

    render() {
        return (
            <div className="request-content">
                <Header 
                icon="arrow_back_ios"
                onClick={this.goBack}
                />
                <div className="description-content">
                    <input type="text" className="title" placeholder="제목을 입력하세요"></input>
                    <div>
                        <span>장소</span>
                        <input type="text" className="place"></input>
                    </div>
                    <span>360 VR 사진을 필수로 올려주세요.</span>
                    <img className="vr-image" />
                    <div className="request-list-header">
                        <span>요구사항</span>
                        <button className="material-icons add-list">add</button>
                    </div>
                </div>
            </div>
        )
    }
}