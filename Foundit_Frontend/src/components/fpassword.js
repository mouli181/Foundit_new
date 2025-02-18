import { Link } from "react-router-dom";
import "../style/fpassword.css"

function Fpassword (){
    return(
        <div className="forget">
            <div className="tablee">
                <leble>Enter Your Name : </leble><input type="text" placeholder="Enter Your Name"></input>
                <leble>Enter Your phoneNo : </leble><input type="Number" placeholder="Enter Your phoneNo"></input>
                <leble>Enter Your Email id : </leble><input type="email" placeholder="Enter Your Email id"></input>
                <leble>Enter Your Whatsapp no : </leble><input type="Number" placeholder="Whatsapp NO"></input>
            </div>
            <input type="Number" placeholder="Enter a OTP" className="otpp"></input>
           <Link to="/Timer"><span>click</span></Link> 

        </div>
    )
}

export default Fpassword;