import React, {Component} from 'react';
import Header from "../Header";
import '../../../css/CompanyIntroduction.css'
export default class CompanyIntroduction extends Component {
    constructor(props) {
        super(props);

        this.state = {
            data: undefined
        }
    }

    componentDidMount = async () => {
        this.getData();
    }

    getData = async () => {
        let { id } = this.props.match.params;
        try {
            let data = await (await fetch(`/api/company/${id}`)).json();
            this.setState({data})
        } catch (e) {
            console.error(e);
        }
    }
    render() {
        if(!this.state.data) return (<div>hi</div>)
        let { data } = this.state;

        return (
            <div className="content company-detail">
                <Header
                    icon="arrow_back_ios"
                    onClick={this.goBack}
                    rightElement={this._renderReverseButton}
                />
                <p className="company-name">{data.member.username}</p>
                <div className="company-description">
                    <p className="company-title">회사설명</p>
                    <p>{data.description}</p>
                </div>
                <span className="company-score">회사평점 {data.score}/5</span>
                <div className="company-chosen-content">
                    <span>입찰에 성공한 횟수</span>
                    <span>{data.chosenCount}건</span>
                </div>
                {this._renderReviewList(data.companyReview)}
            </div>
        )
    }
    _renderReviewList = list => (
        <div className="review-list">
            <p className="review-title">리뷰</p>
            {list.map(this._renderReviewItem)}
        </div>
    )
    _renderReviewItem = (item, index) => (
        <div key={index} className="review-item">
            <span className="review-description">{item.description} - {item.member.username}</span>
            <span>{item.score}점</span>
        </div>
    )
}