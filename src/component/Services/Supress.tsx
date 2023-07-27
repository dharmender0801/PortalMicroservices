import React, { useEffect, useState } from "react";
import { MODEL } from "../Utils/MODEL";
import HttpReq from "../Service/HttpReq";
import { BASE_URL } from "../Utils/Constant";
import { toast } from "react-toastify";

const Suppress = (props: any) => {
    const [columndata, setColumnData] = useState(MODEL.vendor);


    useEffect(() => {
        if ('Choose Advertizer' !== props.vendorName && props.vendorName !== "") {
            getTotal(props.vendorName);
        }
    }, [props.vendorName]
    )
    const getTotal = (e: any) => {
        HttpReq.GetReq(`${BASE_URL}/${props.service}/get/vendor?name=${e}`).then((response) => {
            setColumnData(response.data);
        });
    };
    const handleSubmit = (e: any) => {
        e.preventDefault();
        const formData = new FormData(e.target as HTMLFormElement);
        const formValues: Record<string, string> = {};
        formData.forEach((value, name) => {
            formValues[name] = value as string;
        });
        Update(formValues)
    }

    const Update = (e: any) => {
        HttpReq.PutReq(`${BASE_URL}/${props.service}/update/supress`, e).then((response) => {
            if (response.data.code === 200) {
                toast.success(response.data.statusCode, {
                    position: 'top-right',
                    autoClose: 5000,
                    hideProgressBar: false,
                    closeOnClick: true,
                    pauseOnHover: true,
                    draggable: true,
                    progress: undefined,
                });
            } else {
                toast.error(response.data.statusCode, {
                    position: 'top-right',
                    autoClose: 5000,
                    hideProgressBar: false,
                    closeOnClick: true,
                    pauseOnHover: true,
                    draggable: true,
                    progress: undefined,
                });
            }
        })
    }

    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setColumnData((prevData) => ({
            ...prevData,
            [name]: value,
        }));
        
    };
    return (
        <>
            <div className="table-container">
                <form action="" onSubmit={handleSubmit} method="post">
                    <table >
                        <div className="table">
                            <thead className="bg-primary">
                                <tr style={{ background: props.navColor }}>
                                    <td style={{ padding: "5px 15px", width: "10vw", height: "2.5vw", textAlign: "center", }}>CPID</td>
                                    <td style={{ padding: "5px 15px", width: "10vw", height: "2.5vw", textAlign: "center", }}>Advertizer Name</td>
                                    <td style={{ padding: "5px 15px", width: "15vw", height: "2.5vw", textAlign: "center", }}>Cut</td>
                                    <td style={{ padding: "5px 15px", width: "15vw", height: "2.5vw", textAlign: "center", }}>COUNTER</td>
                                    <td style={{ padding: "5px 15px", width: "15vw", height: "2.5vw", textAlign: "center", }}>Callback Limit</td>
                                    <td style={{ padding: "5px 15px", width: "15vw", height: "2.5vw", textAlign: "center", }}>Daily Capping</td>
                                </tr>
                            </thead>

                            <tbody>
                                <tr style={{ height: "4vw" }}>
                                    <td style={{ padding: "5px 5px", textAlign: "center", height: "3vw", width: "11vw", }}>{columndata.cpId}<input onChange={handleInputChange} name="cpId" hidden value={columndata.cpId} type="text" /></td>
                                    <td style={{ padding: "5px 5px", textAlign: "center", height: "3vw", width: "11vw", }}>{columndata.vendorName}<input onChange={handleInputChange} name="vendorName" hidden value={columndata.vendorName} style={{ height: "3vw", width: "5vw", textAlign: "center" }} type="text" /></td>
                                    <td style={{ padding: "5px 5px", textAlign: "center", }}><input name="cut" onChange={handleInputChange} value={columndata.cut} style={{ height: "3vw", width: "5vw", textAlign: "center" }} type="text" /></td>
                                    <td style={{ padding: "5px 5px", textAlign: "center", }}><input name="counter" onChange={handleInputChange} value={columndata.counter} style={{ height: "3vw", width: "5vw", textAlign: "center" }} type="text" /></td>
                                    <td style={{ padding: "5px 5px", textAlign: "center", }}><input name="callbackLimit" onChange={handleInputChange} value={columndata.callbackLimit} style={{ height: "3vw", width: "5vw", textAlign: "center" }} type="text" /></td>
                                    <td style={{ padding: "5px 5px", textAlign: "center", }}><input name="dailyCapping" onChange={handleInputChange} value={columndata.dailyCapping} style={{ height: "3vw", width: "5vw", textAlign: "center" }} type="text" /></td>
                                </tr>
                            </tbody>
                        </div>
                    </table>
                    <div style={{ marginTop: "3vw" }}><button style={{ backgroundColor: "green", width: "7em", height: "2em", outline: "none", borderRadius: "5px", border: "none", color: "white" }} >Submit  </button>
                        <button style={{ backgroundColor: "red", marginLeft: "1vw", width: "7em", height: "2em", outline: "none", borderRadius: "5px", color: "white", border: "none" }} type="reset">Reset  </button>
                    </div>
                </form>
            </div>

        </>
    )
}
export default Suppress;