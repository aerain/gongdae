import React, { Component } from 'react';
import { Link } from 'react-router-dom';
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

    getData = async () => {
        const {id} = this.props.match.params;
        const uri = `/api/request/${id}`;
        try {
            const item = await (await fetch(uri)).json();
            this.setState({item});
        } catch (err) {
            console.error(err);
        }
    }
    _renderReverseButton = () => (
        <div className="request-header-right-element">
            <Link to={`/company/request/${this.props.match.params.id}`}>경매 제시</Link>
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
    goBack = () => this.props.history.goBack();
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