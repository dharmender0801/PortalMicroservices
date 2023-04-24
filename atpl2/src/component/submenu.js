import React, { useState } from 'react'
import Submenu2 from './submenu2'
import { IoMdArrowDropdown } from 'react-icons/io'
const Submenu = ({ countrydata }) => {


  //Using Inline Function and the The Logical Not (!) to toggle state
  const [toggle, setToggle] = useState(false);
  // const [checking,setChecking] = useState([countrydata])

  return (
    <>


      {/* <span className='slct' onClick={() => setToggle(!toggle)}>{items.countryname}</span> */}
      <button
        onClick={() => setToggle(!toggle)}
        class="sub_btn">
        {countrydata.countryname} <IoMdArrowDropdown />

      </button>
      {toggle && (
        <ul class="list-group">
          <li class="list-group-item nohover">
            {
              countrydata.operator.length > 0 ? countrydata.operator.map((opdata) => <Submenu2 alldata={opdata} />) : null
            }

          </li>
        </ul>
      )}

    </>
  )
}
export default Submenu;
