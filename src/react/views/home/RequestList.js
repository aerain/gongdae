import React, { Component } from 'react';
import Header from "../components/Header";
import '../../css/RequestList.css'
export default class RequestList extends Component {
    constructor(props) {
        super(props);

        this.state = {
            dataSource: {},
            companyList: []
        }
    }

    componentDidMount = async () => {
        this.getDataSource();
        this.getCompanyList();
    }
    getCompanyList = async () => {
        // Todo Fetch
        this.setState({companyList: [{
            id: 1,
            companyName: '제대루',
            
        }]})
    }

    goBack = () => this.props.history.goBack();
    render() {
        let { id, clientId, title, place, imgUrl, companySize } = this.state.dataSource;
        return (
            <div className="request-list-content">
                <Header
                    icon="arrow_back_ios"
                    onClick={this.goBack}
                />
                <p className="title">{title}</p>
                <div style={{backgroundImage: `url(${imgUrl})`}} alt="테스트" className="vr-img"/>
                <div className="sub-list">
                    <span>내 요청리스트</span>
                    <button className="material-icons">arrow_drop_down</button>
                </div>
                <div className="join-company">
                    <div className="company-header">
                        <p>해당 의뢰에</p>
                        <p>{companySize} 개의 회사가 참여해요!</p>
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

    getDataSource = () =>{
        // Todo fetch
        this.setState({dataSource: {
            id: 1,
            clientId: 1,
            title: '내 집 리모델링',
            place: '내 집',
            imgUrl: 'https://freshome.com/wp-content/uploads/2017/07/contrast-living-room-1.jpg',
            companySize: 1
        }});
    }
}