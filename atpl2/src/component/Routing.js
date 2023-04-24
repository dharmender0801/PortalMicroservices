import React, { useState, useEffect } from "react";
import { Routes, Route } from "react-router-dom";
import Countries from "./Countries";
import axios from 'axios';
import bas_url from '../js/callapi';
import Page from './page1';

const Routing = ({ data }) => {
    useEffect(() => {
        getservice();
    }, []);
    const [CountryData, SetCountryData] = useState([]);

    const getservice = () => {
        axios.get(`${bas_url}/country/getAll`).then(
            (response) => {
                SetCountryData(response.data);
            }, (error) => {
                // console.log(error);
            }
        )
    }




    return (
        <div className="Containing">
            <Routes>
                <Route path={data.routeing} element={<Countries servicename={data.servicename} datas={CountryData} routing={data.routeing} />} />
                {/* <Route path={"voicechat/mis"} element={<Countries servicename={data.servicename} datas={CountryData} routing={data.routeing} operator={"congob"} />} /> */}
            </Routes>
        </div>

    )
}
export default Routing;