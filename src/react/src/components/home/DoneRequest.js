import React, { Component } from 'react';
import Header from "../Header";
import renderList from "./renderList";

export default class CompanyDoneRequest extends Component {
    constructor(props) {
        super(props);
        this.state = {
            list: []
        }
    }
    componentDidMount = async () => {
        this.getData();
    }
    getData = async () => {
        try {
            let list = await (await fetch('/api/request/done')).json();
            this.setState({list});
        } catch (err) {
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
                {
                    this.state.list.map(renderList)
                }
            </div>
        )
    }
}