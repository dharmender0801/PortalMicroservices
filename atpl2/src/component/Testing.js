import { React, useState } from "react";
import DatePicker from "react-datepicker";
import './pages.css'

const Testing = (props) => {

  const [formData, setFormData] = useState({});
  const { htmlContent } = props;
  const [startdate, setStartDate] = useState(new Date());
  const [enddate, setEndDate] = useState(new Date());

  const handleSubmit = (event) => {
    const { name, value } = event.target;
    setFormData(prevState => ({ ...prevState, [name]: value }));
    event.preventDefault();
    const formDataArray = [];
    const formData = new FormData(event.target);
    formData.forEach((value, name) => {
      const formDataObject = { name, value };
      formDataArray.push(formDataObject);
    });
    console.log(formDataArray);
  }

  return (
    <form onSubmit={handleSubmit} method="post" style={{ padding: '20px' }}>
      {/* <div dangerouslySetInnerHTML={{ __html: htmlContent }}></div> */}
      <div className="UpperCont">
        <span><label style={{ fontWeight: 600, color: "black" }} >From date</label>
          <DatePicker className="custom-input" name="startdate" selected={startdate} onChange={(date) => setStartDate(date)} />
        </span>
        <span><label style={{ fontWeight: 600, color: "black" }}>To date</label>
          <DatePicker className="custom-input" name="enddate" selected={enddate} onChange={(date) => setEndDate(date)} />
        </span>
        
      </div>
      <button type="submit" style={{ background: "#2756d6", color: "white", borderRadius: ".5em", padding: "4px 10px", border: "none", marginTop: "20px" }}>Submit</button>

    </form>

  );
}
export default Testing;