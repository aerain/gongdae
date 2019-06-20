import {Link} from "react-router-dom";
import React from "react";

let renderList = item => (
    <Link key={item.id} to={`/user/list/${item.id}`} className="list-item">
        <div className="item-bg" style={{backgroundImage: `url(${item.imgUrl})`}} />
        <div style={{flex: 1}} />
        <div className="item-content">
            <p className="item-title">{item.title}</p>
            <p className="item-company-size">{item.companySize} 건의 참여</p>
        </div>

    </Link>
)

export default renderList;