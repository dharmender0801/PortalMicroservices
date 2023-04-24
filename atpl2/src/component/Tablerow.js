import React from "react";
const TableRow = ({ Date }) => {
    return (
        <>
            <tr>
                {
                    Date.values.length > 0 ? Date.values.map((items) => {
                        return (
                            <>
                                <td>{items.count}</td>
                            </>
                        )
                    }
                    ) : null
                }
            </tr>
        </>
    )
}
export default TableRow;