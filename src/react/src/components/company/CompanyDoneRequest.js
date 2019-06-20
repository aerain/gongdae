import React, { Component } from 'react';
import renderList from './renderList';
import Header from "../Header";
import '../../../css/CompanyDoneRequest.css';

export default class CompanyDoneRequest extends Component {
    constructor(props) {
        super(props);
        this.state = {
            list: []
        }
    }

    componentDidMount = () => this.getList();
    getList = async () => {
        try {
            let list = await (await fetch('/api/request/done')).json();
            this.setState({list});
        } catch(err) {
            console.error(err);
        }
    }
    goBack = () => this.props.history.goBack();
    render() {
        return(
            <div className="content done">
                <Header
                    icon="arrow_back_ios"
                    onClick={this.goBack}
                />
                {renderList(this.state.list, '판매완료')}
            </div>
        )
    }
}