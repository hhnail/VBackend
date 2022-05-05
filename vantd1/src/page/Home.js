import React, {useEffect, useState} from "react";

import {Layout, Menu} from 'antd';
import {getHeaderMenu, getSidebar} from "../service/commonService";
import ConfigPlatform from "./ConfigPlatform";
import {Route, Routes, useNavigate} from "react-router-dom";
import ModuleMaintenance from "./ConfigPlatform/SystemTool/ModuleMaintenance";
import DataRestructure from "./ConfigPlatform/SystemTool/DataRestructure";
import StaffDesktop from "./StaffDesktop";
import HumanResource from "./HumanResource";
import MyMessage from "./StaffDesktop/MyMessage";
import MyTodo from "./StaffDesktop/MyTodo";
import SystemTool from "./ConfigPlatform/SystemTool";
import OnlineLearn from "./OnlineLearn";
import SingleCode from "./ConfigPlatform/DataDictionary/SingleCode";
import MultilevelCode from "./ConfigPlatform/DataDictionary/MultilevelCode";

const {Header} = Layout;

export default function Home() {

    // 顶部菜单及当前选中的菜单
    const [headerMenu, setHeaderMenu] = useState()
    const [currentHeader, setCurrentHeader] = useState()
    // 侧边栏及当前选中的侧边栏
    const [sidebar, setSidebar] = useState()
    const [currentSidebar, setCurrentSidebar] = useState()

    // 路由跳转
    const navigate = useNavigate()

    useEffect(() => {
        // 获取顶部菜单
        getHeaderMenu().then(res => {
            const {data} = res
            // console.log("header", data)
            setHeaderMenu(data)
            if (data && data.length > 0) {
                setCurrentHeader(data[0])
            }
        })
    }, [])


    useEffect(() => {
        // 初始化路由及侧边栏
        let routeAddress = '/configPlatform/moduleMaintenance'
        let moduleId = -1;
        if (headerMenu && headerMenu.length > 0) {
            moduleId = headerMenu[0].pid
        }
        // currentHeader非空说明已经选中某个菜单了
        // 按照选中菜单切换路由、获取侧边栏即可
        if (currentHeader != null) {
            routeAddress = currentHeader.url
            moduleId = currentHeader.key
        }
        refreshSideBar(moduleId)
        // 切换路由
        navigate(routeAddress)
    }, [currentHeader])


    useEffect(() => {
        if (currentSidebar) {
            navigate(currentSidebar.url)
        }
    }, [currentSidebar])


    /**
     * 根据模块编码，重新获取侧边栏
     */
    const refreshSideBar = (moduleId) => {
        if (moduleId < 0) {
            return
        }
        // 获取侧边栏
        getSidebar(moduleId).then(res => {
            const {data} = res
            // console.log("sidebar", data)
            setSidebar(data)
        })
    }

    /**
     * 顶部菜单点击事件
     */
    const headerClick = (item) => {
        headerMenu.forEach(menu => {
            if (menu.key == item.key) {
                setCurrentHeader(menu)
                return;
            }
        })
    }

    /**
     * 侧边栏点击事件
     */
    const sidebarClick = (item) => {
        console.log("sidebar item", item)
        // setCurrentSidebar()
        mapRoute(sidebar, item.key)
    }

    /**
     * 匹配路由
     */
    const mapRoute = (routeList, targetKey) => {
        // 当为数组
        if (routeList.length > 0) {
            routeList.forEach((item) => {
                // console.log(item)
                if (item.key == targetKey) {
                    setCurrentSidebar(item)
                    // console.log("map success!",item)
                    return
                } else {
                    if (item.children) {
                        mapRoute(item.children, targetKey)
                    }
                }
            })
        }
    }


    return (<div>
        {/* ============================== home header ============================== */}
        <Header style={{
            size: 18, fontWeight: 800,
        }}>
            {/*先获取头部菜单，才能知道默认高亮的key是多少*/}
            {headerMenu && <Menu items={headerMenu} theme="dark" mode="horizontal"
                // 默认选择首个菜单
                                 defaultSelectedKeys={[headerMenu[0].key]}
                                 onClick={(item) => headerClick(item)}
            />}
        </Header>
        {/* ============================== over home header ============================== */}

        {/* ============================== home body ============================== */}
        <div style={{
            display: 'flex', flexDirection: 'row',
        }}>
            {/* ======= sidebar =======*/}
            <div style={{
                width: '13%',
            }}>
                <Menu mode="vertical"
                      items={sidebar}
                      onClick={(item) => sidebarClick(item)}
                />
            </div>
            {/* ======= over sidebar =======*/}
            <div style={{
                overflow: 'hidden', width: '87%', height: '100%', padding: '10px 0px 10px 0px'
            }}>
                {/* TODO 动态路由 */}
                <Routes>
                    <Route exact path="/" element={<a>login please......</a>}/>
                    {/*===== 员工桌面 =====*/}
                    <Route exact path="/staffDesktop" element={<StaffDesktop/>}/>
                    <Route exact path="/staffDesktop/myMessage" element={<MyMessage/>}/>
                    <Route exact path="/staffDesktop/myTodo" element={<MyTodo/>}/>


                    {/*===== 在线学习 =====*/}
                    <Route exact path="/onlineLearn" element={<OnlineLearn/>}/>

                    {/*===== 人力资本 =====*/}
                    <Route exact path="/humanResource" element={<HumanResource/>}/>


                    {/*===== 配置平台 =====*/}
                    <Route exact path="/configPlatform" element={<ConfigPlatform/>}/>
                    <Route exact path="/configPlatform/systemTool" element={<SystemTool/>}/>
                    <Route exact path="/configPlatform/systemTool/moduleMaintenance" element={<ModuleMaintenance/>}/>
                    <Route exact path="/configPlatform/systemTool/dataRestructure" element={<DataRestructure/>}/>

                    <Route exact path="/configPlatform/dataDictionary" element={<>数据字典</>}/>
                    <Route exact path="/configPlatform/dataDictionary/singleCode" element={<SingleCode/>}/>
                    <Route exact path="/configPlatform/dataDictionary/multilevelCode" element={<MultilevelCode/>}/>
                </Routes>
            </div>
        </div>
        {/* ============================== over home body ============================== */}
    </div>);
}




