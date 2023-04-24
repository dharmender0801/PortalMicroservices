import React from 'react';
import { SiMicrosoftexcel } from "react-icons/si";
import { Table } from 'reactstrap';
import './pages.css';

import axios from 'axios';
import { useEffect, useState } from 'react';
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import bas_url from '../js/callapi';
import TableRow from './Tablerow';
import TableHeading from './tableHeading';




function Page1({ operator, htmlContent, routing }) {
  useEffect(() => {
    const today = new Date();
    const startDate = today.toISOString().slice(0, 10);
    const JsonDta = [
      {
        name: "startdate",
        value: startDate
      },
      {
        name: "enddate",
        value: startDate
      }
    ]
    getservice(JsonDta);

  }, []);

  const getservice = (ArraJson) => {
    setIsLoading(true);
    axios.post(`${bas_url}/${operator}/voicechat`, ArraJson).then(
      (response) => {
        SetHeader(response.data.columns);
        SetRow(response.data.datas);
        setIsLoading(false);
      }, (error) => {
        console.log(error);
        setIsLoading(false);
      }
    )
  }


  const [Row, SetRow] = useState([]);
  const [Header, SetHeader] = useState([]);
  const [startdate, setStartDate] = useState(new Date());
  const [enddate, setEndDate] = useState(new Date());
  const [isLoading, setIsLoading] = useState(true);
  const [formData, setFormData] = useState({});

  const handleFormSubmit = event => {
    event.preventDefault();
    const { name, value } = event.target;
    setFormData(prevState => ({ ...prevState, [name]: value }));
    const formDataArray = [];
    const formData = new FormData(event.target);
    formData.forEach((value, name) => {
      const formDataObject = { name, value };
      formDataArray.push(formDataObject);
    });
    console.log(formDataArray);



    getservice(formDataArray);
  };

  if (routing === "vendor") {
    return (
      <div className='page_1'>
        {isLoading &&
          <div className='Loadermain'>
            <div class="loader">
              <div class="loader-dot"></div>
              <div class="loader-dot"></div>
              <div class="loader-dot"></div>
            </div>
            Fetching....
          </div>}
        <h1>tHIS cHECKING</h1>


      </div>
    );
  }
  else {
    return (
      <div className='page_1'>
        {isLoading &&
          <div className='Loadermain'>
            <div class="loader">
              <div class="loader-dot"></div>
              <div class="loader-dot"></div>
              <div class="loader-dot"></div>
            </div>
            Fetching....
          </div>}


        <form onSubmit={handleFormSubmit}>
          <div className='datepick'>
            <span><label>From date</label>
              <DatePicker className="custom-input" dateFormat="yyyy-MM-dd" name="startdate" selected={startdate} onChange={(date) => setStartDate(date)} />
            </span>
            <span><label>To date</label>
              {/* <Datepick value={enddate} onChange={date => setEndDate(date)} /> */}
              <DatePicker className="custom-input" dateFormat="yyyy-MM-dd" name="enddate" selected={enddate} onChange={(date) => setEndDate(date)} />

            </span>
            <span className="FilterCont">
              <div dangerouslySetInnerHTML={{ __html: htmlContent }}></div>
            </span>
          </div>
          <div className="page-btn">
            <span><button type='submit'>Submit</button></span>
            <span><button className='btn-2' type='Submit'> <SiMicrosoftexcel color='#2B8F00' size={30} /> Download</button></span>
          </div>


        </form>

        <div className='table'>
          <Table hover  >
            <thead>
              <tr>
                {
                  Header.length > 0 ? Header.map((doings) => <TableHeading Head={doings} />) : "Wait"
                }
              </tr>
            </thead>
            <tbody>

              {
                Row.length > 0 ? Row.map((doing) => <TableRow Date={doing} />) : null
              }
            </tbody>
          </Table>
        </div>
      </div>
    );
  }


}

export default Page1;