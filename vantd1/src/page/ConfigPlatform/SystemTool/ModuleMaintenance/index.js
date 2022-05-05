import {Button, Checkbox, Form, Input, InputNumber, message, Modal, Space, Table} from 'antd';
import React, {useEffect, useState} from "react";
import {addModule, deleteModule, getModule, updateModule} from "../../../../service/commonService";
import {useForm} from "antd/es/form/Form";
import {TREE_NODE_TYPE} from "../../../../enums/treeNodeType";
import qs from 'qs'
import {ReloadOutlined} from '@ant-design/icons'


/**
 * 模块管理
 */
export default function ModuleMaintenance() {


    // 模块信息
    const [data, setData] = useState([]);
    // 模态框——新增
    const [addModuleForm] = useForm()
    const [addModalVisible, setAddModalVisible] = useState(false);
    // 模态框——编辑
    const [editModuleForm] = useForm()
    // const [editModuleForm] = Form.useForm()
    const [editModalVisible, setEditModalVisible] = useState(false);
    // 当前操作的节点（方便拿pid、level等信息）
    const [currentItem, setCurrentItem] = useState({});

    const [reloadBtnLoading, setReloadBtnLoading] = useState(false)


    const refreshData = (isByBtn) => {
        // 获取模块信息
        getModule().then(res => {
            const {data} = res
            // console.log("==v6 module list",data)
            setData(data)
            if (isByBtn) {
                setReloadBtnLoading(false)
                message.success("操作成功")
            }
        })
    }

    useEffect(() => {
        refreshData()
    }, [])


    /**
     * 按钮——新增
     */
    const btnAdd = (item) => {
        // 清空表单
        addModuleForm.resetFields();
        setCurrentItem(item)
        // console.log("add currentItem", item)
        setAddModalVisible(true)
    }

    /**
     * 按钮——删除
     */
    const btnDelete = (item) => {
        setCurrentItem(item)
        // 确认操作弹窗
        Modal.confirm({
            title: `您确认要删除[${item.name}]吗？`,
            okText: '确认',
            cancelText: '取消',
            onOk: () => {
                const data = qs.stringify({id: item.key})
                deleteModule(data)
                    .then(() => {
                        message.success("操作成功")
                    })
                    .catch((err) => {
                        message.error("操作失败")
                        console.log("==v5", err)
                    })
                    .finally(() => {
                        refreshData()
                    })
            }
        })
    }


    /**
     * 按钮——编辑
     */
    const btnEdit = (item) => {
        // editModuleForm.resetFields();
        setCurrentItem(item)
        // console.log("edit currentItem", item)
        // editModuleForm.resetFields()
        /*
        表单回显
        form上的initValues不会随着setState改变。而resetFieldValues又有延迟
        input上的defaultValues只有初次渲染时生效
        所有只能这么干了
         */
        const {name, routingAddress, orderId} = item
        const beginIndex = routingAddress.lastIndexOf("/")
        const endIndex = routingAddress.length
        const lastRoutingAddress = routingAddress.substring(beginIndex, endIndex)
        editModuleForm.setFieldsValue({
            name: name,
            routingAddress: lastRoutingAddress,
            orderId: orderId
        })
        setEditModalVisible(true)
    }

    const columns = [
        {
            title: '模块名称',
            dataIndex: 'name',
            key: 'name',
        },
        {
            title: '路由地址',
            dataIndex: 'routingAddress',
            width: '50%',
            key: 'routingAddress',
        },
        {
            title: '操作',
            dataIndex: 'key',
            key: 'key',
            width: '30%',
            render: (key, item) => {
                return <>
                    <Space>
                        <Button size={"small"}
                                type={"primary"}
                                onClick={() => btnAdd(item)}
                        >新增</Button>
                        <Button size={"small"} type={"primary"}
                                onClick={() => btnEdit(item)}
                        >编辑</Button>
                        <Button size={"small"} danger
                                onClick={() => btnDelete(item)}>删除</Button>
                    </Space>
                </>
            },
        },
    ];


    const addModalOk = () => {
        addModuleForm.validateFields()
            .then(res => {
                // console.log(res)
                const {name, routingAddress} = res
                // 关闭模态框
                const data = {
                    // 要新增的节点的pid == 当前操作节点的id
                    pid: currentItem.key,
                    name: name,
                    routingAddress: routingAddress,
                    level: currentItem.level + 1,
                    type: TREE_NODE_TYPE.SIDEBAR.value,
                }
                addModule(data).then(res => {
                    message.success("操作成功")
                }).catch(err => {
                    message.error("操作失败")
                }).finally(() => {
                    setAddModalVisible(false)
                    refreshData()
                })
            })
            .catch(err => {
                console.log("=v3", err)
            })
    }

    /**
     * 编辑模块，点击确定修改
     */
    const editModalOk = () => {
        editModuleForm.validateFields().then(values => {
            console.log('edit!', values)
            console.log('edit currentItem', currentItem)
            console.log('edit currentItem.parentRoutingAddress', currentItem.parentRoutingAddress)
            const {name, routingAddress, orderId} = values;
            const data = {
                id: currentItem.key,
                name: name,
                routingAddress: currentItem.parentRoutingAddress + routingAddress,
                orderId: orderId
            }
            updateModule(data).then(res => {
                message.success("操作成功")
            }).catch(err => {
                message.error(err)
            }).finally(() => {
                setEditModalVisible(false)
                refreshData(false)
                editModuleForm.resetFields()
            })
        }).catch(err => {
            message.error(err)
        })
    }

    return (
        <div style={{
            // display: 'flex',
            // flexDirection: 'row',
        }}>
            <div style={{
                // width: '80%',
            }}>
                <Space>
                    <Button
                        type={"primary"}
                        icon={<ReloadOutlined/>}
                        loading={reloadBtnLoading}
                        onClick={() => {
                            setReloadBtnLoading(true)
                            refreshData(true)
                        }}
                    >刷新</Button>
                </Space>
                <Table dataSource={data}
                       columns={columns}
                       pagination={false}
                />
            </div>


            {/* =================== 新增模态框  =================== */}
            <Modal title="新增模块" visible={addModalVisible}
                   okText={"新增"}
                   cancelText={"取消"}
                   width={780}
                   onOk={addModalOk}
                   onCancel={() => {
                       setAddModalVisible(false)
                       addModuleForm.resetFields();
                   }}>
                <Form name="addModalForm"
                      form={addModuleForm}
                      labelCol={{
                          span: 6,
                      }}
                      wrapperCol={{
                          span: 16,
                      }}
                      autoComplete="off"
                >
                    <Form.Item label="所属模块" name="parentName">
                        <Input disabled placeholder={currentItem.name}/>
                    </Form.Item>

                    <Form.Item label="模块名称" name="name" rules={[
                        {
                            required: true,
                            message: '请输入模块名称',
                        },
                    ]}
                    ><Input/></Form.Item>

                    <Form.Item label="路由地址" name="routingAddress" rules={[
                        {
                            required: true,
                            message: '请输入路由地址',
                        },
                    ]}
                    ><Input addonBefore={currentItem.routingAddress}/>

                    </Form.Item>
                </Form>
            </Modal>


            {/* =================== 编辑模态框 =================== */}
            <Modal title="编辑模块" visible={editModalVisible}
                   okText={"确定"}
                   cancelText={"取消"}
                   width={780}
                   onOk={editModalOk}
                   onCancel={() => {
                       setEditModalVisible(false)
                   }}>
                <Form name="editModuleForm"
                      form={editModuleForm}
                      labelCol={{span: 6,}}
                      wrapperCol={{span: 16,}}
                    // initialValues={{
                    //     name: currentItem.name,
                    //     routingAddress: currentItem.routingAddress,
                    //     orderId: currentItem.orderId,
                    // }}
                      autoComplete="off">
                    <Form.Item label="模块名称" name="name" rules={[
                        {
                            required: true,
                            message: '请输入模块名称',
                        },
                    ]}>
                        <Input/>
                    </Form.Item>
                    <Form.Item label="路由地址" name="routingAddress" rules={[
                        {
                            required: true,
                            message: '请输入路由地址',
                        },
                    ]}>
                        <Input addonBefore={currentItem.parentRoutingAddress}/>
                    </Form.Item>
                    <Form.Item label="排序号" name="orderId">
                        <InputNumber/>
                    </Form.Item>
                </Form>
            </Modal>

        </div>
    )
}

