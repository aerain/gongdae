import React, { Component } from 'react'
import { Link } from 'react-router-dom';
import '../../../css/Home.css'
import Header from '../../components/Header';
import {Drawer} from "../drawer";
import drawerItem from './DrawerItem.json'
import renderList from "./renderList";
export default class Home extends Component {
    constructor(props) {
        super(props);

        this.state = {
            list: [],
            isDrawerOpened: false,
        }
    }
    
    componentDidMount = () => {
        this.getList();
    }

    getList = async () => {
        try {
            let list = await (await fetch('/api/request')).json();
            this.setState({list });
        } catch (err) {
            console.error(err);
        }
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
            <div className="content">
                <Header 
                    icon="menu"
                    onClick={this.toggleDrawer}
                />
                {
                    this.state.list.map(renderList)
                }
                {this._renderFloatButton()}
                <Drawer isOpened={ this.state.isDrawerOpened } action={this.toggleDrawer} list={drawerItem}/>
            </div>
        );
    }

    toggleDrawer = () => this.setState(state => ({isDrawerOpened: !state.isDrawerOpened}));
}