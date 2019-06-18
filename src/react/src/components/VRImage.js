import React, { Component } from 'react'

export default class VRImage extends Component {
    render() {
        let { src, className, onChange } = this.props;
        return (src !== null) ? (
            <img className={className} src={URL.createObjectURL(src)} />
        ) : [
            <label htmlFor="vr-image" key="vr-button" className="material-icons vr-image">add</label>,
            <input type="file" ref="vr-image" key="hidden-vr" id="vr-image" onChange={onChange} style={{display: 'none'}}/>
        ]
    }
}