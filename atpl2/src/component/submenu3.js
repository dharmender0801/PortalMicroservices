import React from "react";
import { Link } from "react-router-dom";
const SubMenu3 = ({ listdata }) => {
    // console.log(listdata);
    // console.log(listdata.dataRouting + "?id=" + listdata.id);
    return (
        <>
            <Link key={listdata.id} tag="a" to={listdata.dataRouting+"/"+listdata.id} action>

                <li class="list-group-item left">{listdata.operatorData}</li>
            </Link>
        </>
    )
}
export default SubMenu3;