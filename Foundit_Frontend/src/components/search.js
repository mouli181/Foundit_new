import "../style/search.css"
import search from '../images/search.png'
import location from '../images/location.png'
import exp from '../images/Expe.png'
function Search() {
    return (

        <div className="contant">
            <h4>Over 7,00,000+jobs to explore</h4>
            <div className="inputs">
                <div className="box">
                    <img src={search} className="seaim" />
                    <input className="asd" type="text" placeholder="Search by job,company or skils" />
                </div>
                <div className="box">
                <div className="line"></div>
                    <img src={location} className="loca"/>
                    <input type="location" placeholder="Location" />
                </div>
                <div className="box">
                    <img src={exp} className="exp" />
                    <input type="input" placeholder="Expreance" />
                </div>
                <button placeholder="Search">Search</button>
            </div>
        </div>

    )
}

export default Search;