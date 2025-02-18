import "../style/footer.css"
import location from "../images/location.png";
import call from "../images/phone.png";
import mail from "../images/mail.png";
import downlod from "../images/download.png";
import stay from "../images/stay.png";
function Footer() {
    return (
        <div className="Footer">
            <ul className="one">
                <li>
                <img src={location} className="imgge" />
                    <span id="location">Chose the location :</span> 
                <select name="Chose the location">
                    <option value="coimbatore">Coimbatore</option>Coimbatore

                    <option value="chennai">Chennai</option>Chennai

                    <option value="bengaluru">Bengalur</option>Bengalur

                </select>
                    <hr className="hr"></hr>
                </li>
                <li>
                <img src={call} className="call"/>
                    Contact No:+7358892432 </li>
                <hr className="hr"></hr>
                <li>
                <img src={mail} className="mail"/>
                hemi2005@gmail.com
                </li>
                <hr className="hr"></hr>
                <li>
                <img src={downlod} className="downlod"/>
                    Download The App
                </li>
                <hr className="hr"></hr>
                <li>
                    <img src={stay} className="stay"/>
                    Stay Connecter
                </li>
                <hr className="hr"></hr>
            </ul>
        </div>
       
        
    )
}
export default Footer;