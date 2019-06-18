import React, { Component } from 'react'
import { Link } from 'react-router-dom';
import '../../../css/Home.css'
import Header from '../../components/Header';
export default class Home extends Component {
    constructor(props) {
        super(props);

        this.state = {
            list: []
        }
    }
    
    componentDidMount = () => {
        this.getList();
    }

    getList = async () => {
        // TODO fetch list
        let response = await fetch('/api/request');
        let data = await response.json();
        this.setState({list: data});
    }

    _renderFloatButton = () => (
        <Link to={`${this.props.match.url}/request`} className="add-float">
            <div className="material-icons add-icon">
                add
            </div>
            <p>버튼을 눌러 견적을 추가하세요</p>
        </Link>
    )

    render() {
        return (
            <div className="home-list">
                <Header 
                    icon="menu"
                    onClick={() => alert("hi")}
                />
                {
                    this.state.list.map(this._renderBlock)
                }
                {this._renderFloatButton()}
            </div>
        );
    }

    _renderBlock = item => (
        <Link key={item.id} to={`${this.props.match.url}/list/${item.id}`} className="list-item">
            <div className="item-bg" style={{backgroundImage: `url(${item.imgUrl})`}} />
            <div style={{flex: 1}} />
            <div className="item-content">
                <p className="item-title">{item.title}</p>
                <p className="item-company-size">{item.companySize} 건의 참여</p> 
            </div>
            
        </Link>
    )
}