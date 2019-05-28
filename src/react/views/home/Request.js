import React, { Component } from 'react'
import Header from '../components/Header';
import '../../css/Request.css'
export default class Request extends Component {
    constructor(props) {
        super(props);

        this.state = {
            requestList: [
                {
                    id: 1,
                    description: "벽은 하얀 벽지가 좋아요.",
                    imgUrl: "https://freshome.com/wp-content/uploads/2017/09/interior-design-living-1.jpg",
                },
                {
                    id: 2,
                    description: "천장 전등을 샹들리에로 교체해 주세요.",
                    imgUrl: ""
                }
            ]    
        };
    }

    componentDidMount = () => {
        
    }

    goBack = () => this.props.history.goBack();

    _requestList = () => (
        <div className="request-list">
            {this.state.requestList.map(this._renderItem)}
        </div>
    )
    
    _renderItem = item => (
        <div className="request-item">
            <p>{item.description}</p>
            {
                (item.imgUrl !== "") ? (
                    <img src={item.imgUrl} className="assist-img" alt="추가 이미지"/>
                ) : (
                    <button className="material-icons assist-img">add</button>
                )
            }
        </div>
    )

    render() {
        return (
            <div className="request-content">
                <Header 
                icon="arrow_back_ios"
                onClick={this.goBack}
                />
                <div className="description-content">
                    <input type="text" className="title" placeholder="제목을 입력하세요"></input>
                    <div className="place-content">
                        <span>장소</span>
                        <input type="text" className="place"></input>
                    </div>
                    <span>360 VR 사진을 필수로 올려주세요.</span>
                    <img className="vr-image" alt="360 이미지"/>
                    <div className="request-list-header">
                        <span>요구사항</span>
                        <button className="material-icons add-list">add</button>
                    </div>
                    {this._requestList()}
                </div>
            </div>
        )
    }
}