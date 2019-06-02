import React, { Component } from 'react';
import Header from "../components/Header";
import '../../css/RequestList.css'
export default class RequestList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            item: {},
            requestList: [],
            companyList: [],
            toggleSubList: false,
        }
    }

    componentDidMount = async () => {
        this.getDataSource();
        this.getRequestList();
        this.getCompanyList();
    }
    getCompanyList = async () => {
        // Todo Fetch
        this.setState({
            companyList: [{
                id: 1,
                companyName: '제대루',

            }]
        })
    }

    getDataSource = async () => {
        const {id} = this.props.match.params;
        const uri = `/api/request-list/${id}`;
        try {
            const {data} = await (await fetch(uri)).json();
            this.setState({item: data});
        } catch (err) {
            console.error(err);
        }
    }
    getRequestList = async () => {
        const {id} = this.props.match.params;
        const uri = `/api/request-detail/${id}`;
        try {
            const {data} = await (await fetch(uri)).json();
            this.setState({requestList: data})
        } catch (err) {
            console.error(err);
        }
    }
    goBack = () => this.props.history.goBack();

    render() {
        let { item } = this.state;
        return (
            <div className="request-list-content">
                <Header
                    icon="arrow_back_ios"
                    onClick={this.goBack}
                />
                <p className="title">{item.title}</p>
                <div style={{backgroundImage: `url(${item.imgUrl})`}} alt="테스트" className="vr-img"/>
                <button className="sub-list" onClick={this.toggleSubList}>
                    <span>내 요청리스트</span>
                    <div className="material-icons" >
                        {this.state.toggleSubList ? "arrow_drop_up" : "arrow_drop_down"}
                    </div>
                </button>
                {this.state.toggleSubList && this._renderSubList()}
                <div className="join-company">
                    <div className="company-header">
                        <p>해당 의뢰에</p>
                        <p>{item.companySize} 개의 회사가 참여해요!</p>
                    </div>
                    {
                        this.state.companyList.map(this._renderCompanyItem)
                    }
                    {this._renderCompanyItem()}
                </div>

            </div>
        );
    }

    _renderCompanyItem = item => (
        <div className="company-item">
            <div className="company-name">제대루</div>
            <div className="estimated-price">500,000원</div>
            <div className="estimated-detail">상세보기</div>
        </div>
    )

    _renderSubList = () => (this.state.requestList.length === 0) ?
        (
            <div className="sub-list-content">없어요</div>
        ) : (
            <div className="sub-list-content">
                {
                    this.state.requestList.map(item => (
                        <div className="sub-list-item">{item.description}</div>
                    ))
                }
            </div>
        )
    toggleSubList = event => this.setState(state => ({toggleSubList: !state.toggleSubList}))
}
