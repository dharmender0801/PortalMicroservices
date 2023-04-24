import React from "react";
import "../css/rectangle.css"

// GrClose
import { AiOutlineClose } from "react-icons/ai";
import SideNav from "./SideNav";
import { useState } from "react";


const Ractangle = (props) => {
    return (
        <div className="background">

            <div className="inner-content">
                <div className="closing" ><span onClick={props.handleClose}>< AiOutlineClose /></span></div>
                <div className="Menu-Container">
                    {
                        props.NavMenuContain.length > 0 ? props.NavMenuContain.map((item) => <SideNav menu={item.servicename} />)
                            : "No item"
                    }
                </div>
            </div>
        </div>
    )
}
export default Ractangle;