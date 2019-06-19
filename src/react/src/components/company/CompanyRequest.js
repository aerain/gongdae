import React, { Component } from 'react';
import Header from "../Header";
import '../../../css/CompanyRequest.css';

export default class CompanyRequest extends Component {
    constructor(props) {
        super(props);
        this.state = {
            item: undefined,
        }
    }
    componentDidMount = async () => {
        this.getData();
    }

    requestReverseToServer = async () => {

    }
    getData = async () => {

        this.setState({item: {
                id  : 1,
                imgUrl: "//117.17.102.226:8080/images/47378736_1881896865241147_2732212856593317888_n.jpg",
                title: '안녕하세욘',
                place: '반가워욘',
                requestDetailList: [{
                    description: "잘있어욘",
                    id:1
                }],
                companySize: 0

        }})
    }
    _renderReverseButton = () => (
        <div className="request-header-right-element">
            <button onClick={this.requestReverseToServer}>경매 제시</button>
        </div>
    )

    _renderItem = () => {
        let { item } = this.state;
        return (
            <div className="request-list">
                <p className="title">{item.title}</p>
                <div className="request-img" style={{backgroundImage: `url(${item.imgUrl})`}}/>
                <div className="sub-request-title">
                    요청 리스트
                </div>
                {this._renderSubList()}
            </div>
        )
    }

    _renderSubListItem = item => (
        <div className="sub-request-item">{item.description}</div>
    )
    _renderSubList = () => {
        let { requestDetailList } = this.state.item;
        return (
            <div className="sub-request">
                {
                    requestDetailList.map(this._renderSubListItem)
                }
            </div>
        )
    }

    render() {
        return(
            <div className="content company-request">
                <Header
                    icon="arrow_back_ios"
                    onClick={this.goBack}
                    rightElement={this._renderReverseButton}
                />
                {
                    (typeof this.state.item === 'undefined')
                        ? (<h1>ㅎㅇ</h1>)
                        : (this._renderItem())
                }
            </div>
        )
    }
}