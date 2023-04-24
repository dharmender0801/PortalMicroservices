import axios from 'axios';
import React, { useEffect, useState } from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import './App.css';
import Countries from "./component/Countries";
import Home from './component/Home';
import './component/countries.css';
import Page from './component/page1';
import bas_url from './js/callapi';
import Hexagon from './component/Hexagon';
import Content from './component/Content';
import Testing from './component/Testing';



const App = () => {

  useEffect(() => {
    document.title ="Africa";
    getservice();
    countries();
  }, []);

  const getservice = () => {
    axios.get(`${bas_url}/service/get`).then(
      (response) => {
        // console.log(response);
        Setservice(response.data)

      }, (error) => {
        console.log(error);
      }
    )
  }
  const countries = () => {
    axios.get(`${bas_url}/country/getAll`).then(
      (response) => {
        SetCountryData(response.data);
      }, (error) => {
        // console.log(error);
      }
    )
  }

  // const htmlContent = ' <div style={{ display: "flex", alignItems: "center" }}> <span><label style={{ fontWeight: 600, color: "black" }} >From date</label> <DatePicker className="custom-input" name="startdate" selected={startdate} onChange={(date) => setStartDate(date)} /> </span> <span><label style={{ fontWeight: 600, color: "black" }}>To date</label> <DatePicker className="custom-input" name="enddate" selected={enddate} onChange={(date) => setEndDate(date)} /> </span> <span> <label style={{ fontWeight: 600, color: "black", display: "flex" }}> Filter</label> <select name="filter" style={{ padding: "7px 30px", borderRadius: "5px" }}> <option >Select</option> <option value="Hourly">Hourly</option> <option value="datewise">DateWise</option> <option value="Current">Current</option> </select> </span> </div> <button type="submit" style={{ background: "#2756d6", color: "white", borderRadius: ".5em", padding: "4px 10px", border: "none" , marginTop:"20px" }}>Submit</button> ';

  const [services, Setservice] = useState([]);
  const [CountryData, SetCountryData] = useState([]);

  return (
    <div className="App">
      <BrowserRouter>
        <Home />
        {/* <Testing  /> */}
        <Routes >
          <Route path='/' element={<Content />} />
          {
            services.length > 0 ? services.map((item) => {
              return (
                <Route path={`${item.routeing}/*`} element={<Countries servicename={item.servicename} datas={CountryData} routing={item.routeing} />} />
              )
            }
            ) : null
          }
        </Routes>
      </BrowserRouter>

    </div>
  );
}

export default App;
