import axios from 'axios';
import { MDBBtn } from 'mdb-react-ui-kit';
import { React, useEffect, useState } from "react";
import { BsJustify } from "react-icons/bs";
import { Link } from "react-router-dom";
import 'react-toastify/dist/ReactToastify.css';
import "../css/choose.css";
import "../css/content.css";
import bas_url from '../js/callapi';
import Ractangle from "./Ractangle";

const Home = () => {
    useEffect(() => {
        getservice();
    }, []);

    const getservice = () => {
        axios.get(`${bas_url}/service/get`).then(
            (response) => {
                Setservice(response.data)
            }, (error) => {
                console.log(error);
            }
        )
    }




    const [services, Setservice] = useState([]);
    const [openMobileNav, SetNav] = useState(false);
    const toggleSideView = () => {
        SetNav(!openMobileNav);
    }



    return (<>
        <nav class="flex-div back">
            <div className="menu-button">
                <div >
                    < BsJustify onClick={toggleSideView} size={"30px"} />
                    {openMobileNav && < Ractangle handleClose={toggleSideView} NavMenuContain={services} />}
                </div>
            </div>
            <div class="nav-left flex-div">
                <Link tag="a" to={"/"}>
                    <p> Africa </p>
                </Link>
            </div>
            <div class="nav-button">
                {
                    services.length > 0 ? services.map((item) => {
                        return (
                            <Link tag="a" to={item.routeing} style={{ color: "#616161" }} action>
                                <p >{item.servicename}</p>
                            </Link>
                        )
                    }
                    )
                        : null
                }
            </div>
            <MDBBtn rounded style={{ backgroundColor: '#2756d6' }} >
                Logout
            </MDBBtn>
        </nav>
    </>

    )
}
export default Home;