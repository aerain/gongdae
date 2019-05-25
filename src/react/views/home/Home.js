import React, { Component } from 'react'
import Header from '../components/Header';
import '../../css/Home.css'
export default class Home extends Component {

    _renderFloatButton = (child) => (
        <button className="material-icons add-float">{child}</button>
    )

    render() {
        return (
            <div className="home-list">
                hi!
                {this._renderFloatButton("add")}
            </div>
        );
    }
}