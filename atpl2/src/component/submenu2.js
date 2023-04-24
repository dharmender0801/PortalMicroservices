import React, { useState } from 'react'
import { IoMdArrowDropdown } from 'react-icons/io'
import SubMenu3 from './submenu3'


const Submenu2 = ({ alldata }) => {

  //Using Inline Function and the The Logical Not (!) to toggle state
  const [toggle, setToggle] = useState(false)

  return (
    <>
      {/* <span className='slct' onClick={() => setToggle(!toggle)}>Country</span> */}
      <button
        onClick={() => setToggle(!toggle)}
        class="sub_btn second-btn">
        {alldata.name} <IoMdArrowDropdown />
      </button>
      {toggle && (
        <ul class="list-group list-group2">

          {
            alldata.operatordata.length > 0 ? alldata.operatordata.map((lists) => <SubMenu3 listdata={lists} />) : null
          }
         
        </ul>
      )}
    </>
  )
}
export default Submenu2;
