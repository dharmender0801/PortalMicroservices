import React from "react";
import { Link } from "react-router-dom";
import "../css/rectangle.css"
const SideNav = ({ menu }) => {
    return (
        <span>
            <Link tag="a" style={{ color: "#616161" }} action>
                <p>
                    {menu}
                </p>

            </Link>
        </span>
    );
};
export default SideNav;