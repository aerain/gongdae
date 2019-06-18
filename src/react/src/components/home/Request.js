import React, { Component } from 'react'
import Header from '../Header';
import '../../../css/Request.css'
import VRImage from '../../components/VRImage';
import { Redirect } from 'react-router-dom';

export default class Request extends Component {
    constructor(props) {
        super(props);

        this.state = {
            title: "",
            place: "",
            vrImgUrl: null,
            requestList: [
            ],
            isSaved: false
        };
    }

    goBack = () => this.props.history.goBack();

    _requestList = () => (
        <div className="request-list">
            {this.state.requestList.map(this._renderItem)}
        </div>
    )
    changeRequestValue = (index, event) => {
        let description = event.currentTarget.value;
        this.setState(prevState => {
            prevState.requestList[index].description = description;
            return prevState;
        });
    }

    renderPreviewImage = (index, event) => {
        let img = event.target.files[0];
        this.setState(prevState => {
            prevState.requestList[index].assistimgUrl = img;
            return prevState;
        })
    }
    
    _renderItem = (item, index) => (
        <div key={index} className="request-item">
            <input 
            type="text" 
            className="request-item-content" 
            value={item.description} 
            onChange={e => this.changeRequestValue(index, e)}    
            />
        </div>
    )

    addRequestList = () => this.setState(prevState => {
        prevState.requestList.push({
            description: '',
            // assistimgUrl: null,
        });
        return prevState;
    })
    changeText = (event, key) => {
        const value = event.currentTarget.value;
        this.setState(prevState => {
            prevState[key] = value;
            return prevState;
        });
    }

    changeVRImage = e => {
        let value = e.target.files[0];
        this.setState(state => {
            state.vrImgUrl = value;
            console.log(state);
            return state;
        });
    }

    saveRequestToServer = async () => {
        const { title, place, vrImgUrl, requestList } = this.state;
        const sendData = new FormData();
        sendData.append('title', title);
        sendData.append('place', place);
        sendData.append('vrImgUrl', vrImgUrl);
        sendData.append('requestList', JSON.stringify(requestList));

        const options = {
            method: 'POST',
            headers: {
                // 'Content-Type': 'application/json',
                'Charset': 'UTF-8'
            },
            body: sendData
        }

        delete options.headers['Content-Type'];
        try {
            let response = await fetch('/api/request', options);
            console.log(response);
            this.setState({isSaved: true})
        } catch(err) {
            console.error(err);
        }
        
    }

    _renderSaveButton = () => (
        <div className="request-header-right-element">
            <button onClick={this.saveTempRequestToServer}>임시 저장</button>
            <button onClick={this.saveRequestToServer}>저장</button>
        </div>
    )
    render() {
        return (this.state.isSaved) ? <Redirect to="/user" /> : (
            <div className="request-content">
                <Header 
                icon="arrow_back_ios"
                onClick={this.goBack}
                rightElement={this._renderSaveButton}
                />
                <div className="description-content">
                    <input type="text" className="title" placeholder="제목을 입력하세요" onChange={e => this.changeText(e, "title")}></input>
                    <div className="place-content">
                        <span>장소</span>
                        <input type="text" className="place" onChange={e => this.changeText(e, "place")}></input>
                    </div>
                    <span>360 VR 사진을 필수로 올려주세요.</span>
                    <VRImage className="vr-image" src={this.state.vrImgUrl} onChange={this.changeVRImage}/>
                    <div className="request-list-header">
                        <span>요구사항</span>
                        <button 
                        className="material-icons add-list"
                        onClick={this.addRequestList}
                        >add</button>
                    </div>
                    {this._requestList()}
                </div>
            </div>
        )
    }
}