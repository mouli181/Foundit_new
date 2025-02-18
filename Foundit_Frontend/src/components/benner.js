import "../style/benner.css"
import benner from "../images/lep.png"
import add from "../images/add.png"
function Benner() {
    return (
        <div>
            <img src={benner} className="benner"/>
            <img src={add} className="add"/>
        </div>
       
        
    )
}

export default Benner;