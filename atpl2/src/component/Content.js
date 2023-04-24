import { React, useEffect, useState } from "react";
import { Link } from "react-router-dom";
import "../css/content.css";
import bas_url from '../js/callapi';
import axios from 'axios';
// import Choose from "./Choose";
const Content = ({ data, rout }) => {

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

    return (
        <div className="back-cont">
            <div class="main">
                {
                    services.length > 0 ? services.map((item) => {
                        return (
                            <Link tag="a" to={item.routeing} action>
                                <div class="hex">
                                    <div>
                                        <span>
                                            {item.servicename}
                                        </span>
                                    </div>
                                </div>
                            </Link>
                        )
                    }
                    ) : null
                }
                <Link tag="a" >
                    <div class="hex">
                        <div>
                            <span>
                                Coming Soon
                            </span>
                        </div>
                    </div>
                </Link>
            </div>
        </div>


    )
}
export default Content;