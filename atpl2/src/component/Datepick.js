import React, { useState } from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";


const Datepick = () => {
  const [startDate, setStartDate] = useState(new Date());
  return (
    <DatePicker className="custom-input" selected={startDate} onChange={(date) => setStartDate(date)} />
  );
}
export default Datepick;