import React from "react";
const TableHeading = ({ Head }) => {
    // console.log(Head);
    return (

        <>
            {

                <th> {Head.columnName}</th>


            }

        </>
    )
}
export default TableHeading;