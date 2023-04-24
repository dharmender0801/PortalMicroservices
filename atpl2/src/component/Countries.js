import React from 'react';
import { TbLayoutDashboard } from "react-icons/tb";
import { Route, Routes } from "react-router-dom";
import './countries.css';
import Page from './page1';
import SubMenu from './submenu';
import Testing from './Testing';

const Countries = ({ datas, servicename, routing, operator }) => {
    document.title = servicename;
    const htmlContent = '<label className="filter"> Filter</label><select name="filter">      <option >Select</option>      <option value="Hourly">Hourly</option>      <option value="datewise">DateWise</option>      <option value="Current">Current</option>    </select>';
    const concheck = '';
    return (
        <>
            <div className='countries_wrap'>
                <div className='sideMenu'>
                    <div className='sideMenu_top'>
                        <span><TbLayoutDashboard size={"3em"} /></span>
                        <p>{servicename}</p>
                    </div>
                    <div className='sideMenu_bottom'>
                        {
                            datas.length > 0 ? datas.map((item) => <SubMenu countrydata={item} />) : null
                        }
                    </div>
                </div>
                <div className='right_cont'>
                    {
                        datas.length > 0 ? datas.map((item) => {
                            return (
                                item.operator.length > 0 ? item.operator.map((opdata) => {
                                    return (

                                        opdata.operatordata.length > 0 ? opdata.operatordata.map((lists) => {
                                            // console.log(lists);
                                            if (lists.dataRouting === "mis") {
                                                return (
                                                    <Routes>

                                                        <Route path={`${lists.dataRouting}/${lists.id}`} element={<Page operator={"Airtel-CongoB"} htmlContent={concheck} routing={lists.dataRouting} />} />

                                                    </Routes>


                                                )
                                            } else {
                                                return (
                                                    <Routes>
                                                        <Route path={`${lists.dataRouting}/${lists.id}`} element={<Page routing={lists.dataRouting} htmlContent={htmlContent} />} />
                                                    </Routes>
                                                )
                                            }
                                        }
                                        ) : null
                                    )

                                }
                                ) : null
                            )
                        }


                        ) : null
                    }
                </div >

            </div>
        </>

    );

}
export default Countries;