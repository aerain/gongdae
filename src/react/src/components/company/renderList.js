import {Link} from "react-router-dom";
import React from "react";

let renderList = (list, onsale) => {
    if(Array.isArray(list) && !list.length) return (
        <div>없어요</div>
    )

    return (
        <div className="for-sale-list">
            {
                list.map(item => (
                    <Link to={`/company/list/${item.id}`} className="row-sale">
                        <div className="sale-image" style={{backgroundImage: `url(${item.imgUrl}`}}/>
                        <div className="list-content">
                            <div>
                                <div className="title">{item.title}</div>
                                <div className="place">{item.place}</div>
                            </div>
                            <div className="on-sale">{onsale}</div>
                        </div>
                    </Link>
                ))
            }
        </div>
    )
}

export default renderList;