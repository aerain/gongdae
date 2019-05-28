import React, { Component } from 'react'
import '../../css/Header.css'

export default class Header extends Component {
    render() {
        return (
            <header className="header">
                <button 
                    className="material-icons menu"
                    onClick={this.props.onClick}
                >
                    {this.props.icon}
                </button>
                {(typeof this.props.rightElement !== 'function') ? this.props.rightElement() : null}
            </header>
        )
    }
}