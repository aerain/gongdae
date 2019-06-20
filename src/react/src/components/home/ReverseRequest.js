import React, { Component } from 'react';
import {Redirect} from 'react-router-dom';
import Header from "../Header";
import '../../../css/ReverseRequest.css';

export default class ReverseRequest extends Component {

    constructor(props) {
        super(props);

        this.state = {
            dataSource: null,
            isConfirm: false
        }
    }
    componentDidMount = async () => {
        this.getDataSource();
    }

    getDataSource = async () => {
        let {id} = this.props.match.params;
        try {
            const url = `/api/reverse/${id}`;
            let dataSource = await (await fetch(url)).json();
            this.setState({dataSource});
        } catch (e) {
            console.error(e);
        }
    }

    goBack = () => this.props.history.goBack();

    _renderEstimatedList = item => (
        <div className="estimated-item">
            <div className="item-title">{item.name}</div>
            <div className="item-price">{item.price}</div>
            <a className="item-redirect" href={item.redirectUri}>상세보기</a>
        </div>
    )

    render() {
        if(this.state.isConfirm) return <Redirect to="/user" />;
        if(this.state.dataSource === null) return (<div>안돼</div>);
        let {id, company, request, estimateList, price, chosen} = this.state.dataSource;
        return (
            <div className="content reverse-request">
                <Header
                    icon="arrow_back_ios"
                    onClick={this.goBack}
                />
                <span className="title">
                        <span className="company-name">{company.companyName}</span>
                        견적
                    </span>
                <div className="vr-image" style={{backgroundImage: `url(${request.imgUrl}`}}/>
                <span className="estimated-title">견적서</span>
                {
                    estimateList.map(this._renderEstimatedList)
                }
                <span className="total-price">도합 {price}원</span>
                {this._renderCompanyIntroduction(company)}
                {!chosen && this._renderSubmitButton(request.id, id)}
            </div>
        )
    }
    _renderCompanyIntroduction = company => (
        <div className="company-introduction">
            <span className="company-score">회사 평점 </span>
        </div>
    )


    _renderSubmitButton = (requestId, id) => (<button className="reverse-submit" onClick={e => this.submit(requestId, id)}>견적선택하기</button>)

    submit = async (requestId, reverseAuctionId) => {
        const uri = `/api/request/confirm`;
        try {
            let response = await fetch(uri, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Charset': 'UTF-8'
                },
                body: JSON.stringify({ requestId, reverseAuctionId })
            });
            if(response.ok)
                this.setState(state => ({isConfirm: !state.isConfirm}))
        } catch (err) {
            console.error(err);
        }


    }
}