import { useState, useEffect } from 'react'
import "../style/header.css"
import logoo from "../images/img1.png"
import contact from"../images/contact.png"
import profile from "../images/profile.png"
import { Link } from "react-router-dom"
import axios from 'axios'
import { useCookies } from 'react-cookie'

function Header() {

    const [userName, setUserName] = useState('')
    const [cookies, _, removeCookie] = useCookies(['token'])

    useEffect(()=>{getUserDetails()}, [])

    const getUserDetails = async() => {
        if(cookies.token!=undefined){
            try {
                let response = await axios.get('http://localhost:8888/user/getUserDetails', {headers: {'Authorization': 'Bearer '+cookies.token}})
                if(response.status === 200){
                    setUserName(response.data.userName)
                }
            } catch (e) {
                if(e.message === "Network Error")
                    alert("Server is Not Working, please try again later....!")
                console.log(e)
            }
        }
      }

    return (
        <header>
            <nav>
                <header>
                    <div className="left">
                        <img src={logoo} className="imgg" />
                        <ul>
                            <li>Jobs</li>
                            <li>Boost</li>
                            <li>prep</li>
                            <li>learn</li>
                            <li>cereer Advice</li>
                            <li>certifications</li>
                        </ul>
                    </div>
                    <div className="rigth">
                        {userName === ''?(<><Link to="/login" className="loginbtn">
                        <img src={contact} />
                        Login</Link>
                        <button className="registerbtn">
                        <Link to="/register" className="reg">
                            <img src={profile} />
                            Register
                        </Link>
                        </button></>):(<><h1 className='userName' style={{ paddingLeft: '20px' }}>{userName}</h1><button type='button' style={{ cursor: 'Pointer' }} onClick={()=>{removeCookie('token');window.location.reload()}}>Log Out</button></>)}
                        <div className="hr"></div>
                        <button id="emplogin">Employer Login</button>
                    </div>
                </header>

            </nav>
        </header>
    )
}

export default Header;