import React, { Component } from 'react';
import {Link, Redirect} from 'react-router-dom';
import Header from "../Header";
import '../../../css/ReverseRequest.css';

export default class ReverseRequest extends Component {

    constructor(props) {
        super(props);

        this.state = {
            dataSource: null,
            isConfirm: false,
            isReview: false,
            review: "",
            reviewScore: 5,
            wroteReview: false,
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
        if(this.state.isConfirm || this.state.wroteReview) return <Redirect to="/user" />;
        if(!this.state.dataSource) return (<div>안돼</div>);
        let {id, company, request, estimateList, price, chosen} = this.state.dataSource;
        return (
            <div className="content reverse-request">
                <Header
                    icon="arrow_back_ios"
                    onClick={this.goBack}
                />
                <span className="title">
                        <span className="company-name">{company.member.username}</span>
                        견적
                </span>
                <div className="vr-image" style={{backgroundImage: `url(${request.imgUrl}`}}/>
                <span className="estimated-title">견적서</span>
                {
                    estimateList.map(this._renderEstimatedList)
                }
                <span className="total-price">도합 {price}원</span>
                {this._renderCompanyIntroduction(company)}
                {chosen ? this._renderReviewButton(request.id, id): this._renderSubmitButton(request.id, id)}
                {this.state.isReview && this._renderReview()}
            </div>
        )
    }
    _renderReview = () => (
        <div className="reverse-review">
            <div className="review-header">
                <button className="material-icons review-exit" onClick={this.toggleReview}>close</button>
            </div>
            <span className="review-title">리뷰를 입력해주세요</span>
            <textarea className="review-area" onChange={this.changeReview} placeholder="여기에 입력하세요."/>
            <div className="review-score-content">
                <span>당신의 점수는?</span>
                <select className="review-score" onChange={this.changeReviewScore}value={this.state.reviewScore}>
                    <option value="1">1점</option>
                    <option value="2">2점</option>
                    <option value="3">3점</option>
                    <option value="4">4점</option>
                    <option value="5">5점</option>
                </select>
            </div>
            <button className="review-submit" onClick={this.submitReview}>리뷰 작성하기</button>
        </div>
    )
    changeReview = e => this.setState({review: e.target.value});
    changeReviewScore = e => this.setState({reviewScore: parseInt(e.target.value)});
    submitReview = async () => {
        const companyId = this.state.dataSource.company.id,
            description = this.state.review,
            score = this.state.reviewScore;
        let res = await (await fetch(`/api/company/${companyId}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Charset': 'UTF-8'
            },
            body: JSON.stringify({ description, score })
        }));
        if(res.ok) this.setState({wroteReview: true})
    }

    _renderReviewButton = (requestId, id) => (
        <button className="reverse-submit" onClick={e => this.toggleReview(requestId, id)}>
            리뷰 작성하기</button>)
    toggleReview = () => this.setState(state => ({isReview: !state.isReview}))
    _renderCompanyIntroduction = company => (
        <div className="company-introduction">
            <span className="company-score">회사 평점 {company.score}/5</span>
            <Link to={`/detail/company/${company.id}`} className="company-more">{company.member.username} 회사 설명 더보기</Link>
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