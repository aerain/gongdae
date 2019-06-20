import React, { Component } from 'react';
import '../../../css/Drawer.css'
import {Link} from "react-router-dom";
export default class Drawer extends Component {

    _renderRow = ({text, uri}) => (
        <Link key={text} className="drawer-item" to={uri} >{text}</Link>
    )
    render() {

        return (this.props.isOpened &&
            <div className="drawer">
                <div className="drawer-header">
                    <button className="material-icons close" onClick={this.props.action}>close</button>
                </div>
                <div className="drawer-content">
                    {this.props.list.data.map(this._renderRow)}
                </div>
                <footer className="drawer-footer">
                    <a href="/logout" className="logout"><i className="material-icons">exit_to_app</i> 로그아웃</a>
                </footer>
            </div>
        )
    }
}