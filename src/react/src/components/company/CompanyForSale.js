import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import Header from "../Header";
import '../../../css/CompanyForSale.css';
import {Drawer} from "../drawer";
import drawerItem from './DrawerItem.json'
import renderList from './renderList';

export default class CompanyForSale extends Component {
    constructor(props) {
        super(props);
        this.state = {
            list: [],
            isDrawerOpened: false,
        }
    }

    componentDidMount = async () => {
        this.getList();
    }

    getList = async () => {
        try {
            let list = await (await fetch('/api/request')).json();
            this.setState({list});
        } catch(err) {
            console.error(err);
        }
    }

    render() {
        return(
            <div className="content company-for-sale">
                <Header
                    icon="menu"
                    onClick={this.toggleDrawer}
                />
                {renderList(this.state.list, '판매중')}
                <Drawer isOpened={this.state.isDrawerOpened} action={this.toggleDrawer} list={drawerItem}/>
            </div>
        )
    }

    toggleDrawer = () => this.setState(state => ({isDrawerOpened: !state.isDrawerOpened}));
}