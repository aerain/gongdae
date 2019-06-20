import React, { Component } from 'react';
import Header from "../Header";
import '../../../css/CompanyReverseRequest.css'

export default class CompanyReverseRequest extends Component {
    constructor(props) {
        super(props);
        this.state = {
            item: undefined,
            estimateList: [],
            isRequested: false,
        }

    }
    componentDidMount = async () => {
        this.getItem();
    }

    getItem = async () => {
        const {id} = this.props.match.params;
        const uri = `/api/request/${id}`;
        try {
            const item = await (await fetch(uri)).json();
            this.setState({item});
        } catch (err) {
            console.error(err);
        }
    }

    _renderRequestInfo = item => (
        <div className="request-detail">
            <div className="request-info">
                <div className="request-img" style={{backgroundImage: `url(${item.imgUrl}`}}/>
                <div className="request-con">
                    <span className="title">{item.title}</span>
                    <span className="place">장소 : {item.place} </span>
                    <span className="company-size">현재 경쟁중인 다른 업체 수 : {item.companySize} </span>
                </div>
            </div>
            <div className="request-detail-list">
                <div className="title">요구 리스트</div>
                {this.state.item.requestDetailList.map(this._renderRequestDetailItem)}
            </div>
        </div>

    )
    _renderRequestDetailItem = (item, index) => (
        <div className="detail-row" key={index}>{item.description}</div>
    )

    _renderReverseButton = () => (
        <div className="request-header-right-element">
            <button onClick={this.requestReverse}>경매 시작</button>
        </div>
    )
    requestReverse = async () => {
        const { estimateList } = this.state;
        if(estimateList.isEmpty()) return;

        try {
            const { id } = this.props.match.params.id;
            let res = await fetch('/api/reverse', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Charset': 'UTF-8'
                },
                body: JSON.stringify({
                    requestId: id,
                    estimateList
                })
            });
            if(res.ok)
                this.setState({isRequested: true});
        } catch(err) {
            console.error(err);
        }
    }
    render() {
        return (
            <div className="content company-reverse-request">
                <Header
                    icon="arrow_back_ios"
                    onClick={this.goBack}
                    rightElement={this._renderReverseButton}
                />
                {this.state.item && this._renderRequestInfo(this.state.item)}
                <div className="reverse-content">
                    <div className="reverse-content-header">
                        <span className="title">견적서</span>
                        <button
                            className="material-icons add-list"
                            onClick={this.addReverseList}
                        >add</button>
                    </div>
                    {this.state.estimateList && this.state.estimateList.map(this._renderReverseEstimateItem)}
                </div>
            </div>

        )
    }

    addReverseList = () => this.setState(state => {
        state.estimateList.push({
            name: "",
            price: 0,
            redirectUri: "",
        });
        return state;
    });


    _renderReverseEstimateItem = (item, index) => (
        <div className="estimate-row" key={index}>
            <label htmlFor="product-name">상품명</label>
            <input type="text" id="product-name" onChange={e => this.changeProductName(e.currentTarget.value, index)}/>
            <label htmlFor="product-price">상품 가격</label>
            <input type="number" id="product-price" onChange={e => this.changeProductPrice(e.currentTarget.value, index)}/>
            <label htmlFor="product-uri">상품 참고 주소</label>
            <input type="text" id="product-uri" onChange={e => this.changeProductUri(e.currentTarget.value, index)}/>
        </div>
    )

    changeProductName = (name, index) => this.setState(({estimateList}) => {
        estimateList[index].name = name;
        return {estimateList}
    })
    changeProductPrice = (price, index) => this.setState(({estimateList}) => {
        estimateList[index].price = parseInt(price);
        return {estimateList}
    })
    changeProductUri = (redirectUri, index) => this.setState(({estimateList}) => {
        estimateList[index].redirectUri = redirectUri;
        return {estimateList}
    })
    goBack = () => this.props.history.goBack();
}
