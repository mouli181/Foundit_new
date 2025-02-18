import "../style/company.css"
import chubb from "../images/chubb.webp"
import congnizant from "../images/cognizant.webp"
import hdfc from "../images/hdfc.webp"
import kpmg from "../images/kpmg.webp"
import tcs from "../images/tcs.jpeg"
function Company() {
    return (
        <div>
            <hr></hr>
            <div className="flex">
                <h4>Featured Companys</h4>
                <div className="linee"></div>
                <img src={chubb} className="chub" />
                <img src={congnizant} className="cong" />
                <img src={hdfc} className="hdfc" />
                <img src={kpmg} className="kpmg" />
                <img src={tcs} className="tcs" />

            </div>
            <hr></hr>
        </div>

    )
}

export default Company;