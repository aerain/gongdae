import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import Header from "../Header";
import '../../../css/CompanyForSale.css';

export default class CompanyForSale extends Component {
    constructor(props) {
        super(props);
        this.state = {
            list: []
        }
    }

    componentDidMount = async () => {
        this.getList();
    }

    getList = async () => {
        let list = await (await fetch('/api/request')).json();
        this.setState({list});
    }

    _renderList = () => {
        let { list } = this.state;
        return (
            <div className="for-sale-list">
                {
                    list.map(item => (
                        <Link to={`${this.props.match.url}/list/${item.id}`} className="row-sale">
                            <div className="sale-image" style={{backgroundImage: `url(${item.imgUrl}`}}/>
                            <div className="list-content">
                                <div>
                                    <div className="title">{item.title}</div>
                                    <div className="place">{item.place}</div>
                                </div>
                                <div className="on-sale">판매중</div>
                            </div>
                        </Link>
                    ))
                }
            </div>
        )
    }

    render() {
        return(
            <div className="content company-for-sale">
                <Header
                    icon="menu"
                    onClick={() => alert("hi")}
                />
                {this._renderList()}
            </div>
        )
    }
}