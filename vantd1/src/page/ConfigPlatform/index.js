import DataRestructure from "./SystemTool/DataRestructure";
import ModuleMaintenance from "./SystemTool/ModuleMaintenance";
import {Alert} from "antd";

export default function ConfigPlatform() {
    return (
        <div>
            <Alert message="welcome to ConfigPlatform" type="success"
                   style={{
                       width:300,
                       height:100,
                   }}/>
        </div>
    )
}

