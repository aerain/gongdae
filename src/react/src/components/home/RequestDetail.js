import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import Header from "../Header";
import '../../../css/RequestList.css'
export default class RequestDetail extends Component {
    constructor(props) {
        super(props);
        this.state = {
            item: {},
            companyList: [],
            toggleSubList: false,
        }
    }

    componentDidMount = async () => {
        this.getDataSource();
        this.getCompanyList();
    }

    getCompanyList = async () => {
        // Todo Fetch
        const {id} = this.props.match.params;
        const uri = `/api/request/${id}/reverse`;
        try {
            const companyList = await (await fetch(uri)).json();
            this.setState({companyList});
        } catch (err) {
            console.error(err);
        }
    }

    getDataSource = async () => {
        const {id} = this.props.match.params;
        const uri = `/api/request/${id}`;
        try {
            const item = await (await fetch(uri)).json();
            this.setState({item});
        } catch (err) {
            console.error(err);
        }
    }

    goBack = () => this.props.history.goBack();

    render() {
        let { item } = this.state;
        return (
            <div className="content request-list-content">
                <Header
                    icon="arrow_back_ios"
                    onClick={this.goBack}
                />
                <p className="title">{item.title}</p>
                <a href={`/vr?image_url=${encodeURIComponent(item.imgUrl)}`} style={{backgroundImage: `url(${item.imgUrl})`}} alt="테스트" className="vr-img"/>
                <button className="sub-list" onClick={this.toggleSubList}>
                    <span>내 요청리스트</span>
                    <div className="material-icons" >
                        {this.state.toggleSubList ? "arrow_drop_up" : "arrow_drop_down"}
                    </div>
                </button>
                {this.state.toggleSubList && this._renderSubList()}
                {(item.sold) ? (
                    <div className="join-company">
                        <div className="company-header">
                            <p>현재 다음 회사의</p>
                            <p>경매 견적을 받았어요!</p>
                        </div>
                        {
                            this.state.companyList.filter(item => item.chosen).map(this._renderCompanyItem)
                        }
                    </div>
                ) : (
                    <div className="join-company">
                        <div className="company-header">
                            <p>해당 의뢰에</p>
                            <p>{item.companySize} 개의 회사가 참여해요!</p>
                        </div>
                        {
                            this.state.companyList.map(this._renderCompanyItem)
                        }
                    </div>
                )}


            </div>
        );
    }

    _renderCompanyItem = item => {
        let { match } = this.props;
        let { company, price, id } = item;
        return (
            <div className="company-item">
                <div className="company-name">{company.member.username}</div>
                <div className="estimated-price">{price}</div>
                <Link to={`/user/reverse/${id}`} className="estimated-detail">상세보기</Link>
            </div>
        )
    }

    _renderSubList = () => (this.state.item.requestDetailList.length === 0) ?
        (
            <div className="sub-list-content">없어요</div>
        ) : (
            <div className="sub-list-content">
                {
                    this.state.item.requestDetailList.map(item => (
                        <div className="sub-list-item">{item.description}</div>
                    ))
                }
            </div>
        )
    toggleSubList = event => this.setState(state => ({toggleSubList: !state.toggleSubList}))
}
