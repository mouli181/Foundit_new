import React  from "react";
import Timer from "./timer";
function Parent(){
    const messagee = "hello";
    return(
        <Timer message = {messagee}/>
    );
}
export default Parent;