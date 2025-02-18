import "../style/login.css"
import computer from "../images/logcs.png"
import logim from "../images/logim.webp"
import google from "../images/google.png"
import linkedin from "../images/linkedin.png"
import fb from "../images/facebook.png"
import gpaly from "../images/gplay.png"
import store from "../images/appstore.png"
import call from "../images/telephone.png"
import mail from "../images/mail.png"
import { Link, useNavigate } from "react-router-dom"
import axios from 'axios'
import { useCookies } from 'react-cookie'

function Login() {

    const Navigate = useNavigate()
    const [_, setCookie ] = useCookies(['token'])

    const submit = async (event) => {
        event.preventDefault()
        const loginData = {
          userName: event.target[0].value,
          password: event.target[1].value
        }
        if(validation(loginData)){
          if(await send(loginData)){
            Navigate('/')
          }
        }
      }
    
      // Input validation function
      function validation(loginData) { 
        const passwordPattern = /^.{8,}$/
        if(loginData.userName.length < 2){
          return false
        }else if(!passwordPattern.test(loginData.password)){
          return false
        }else {
          return true
        }
      }
    
      // Login Api Call
      async function send(loginData){
        try{
          let response = await axios.post('http://localhost:8888/login',loginData)
          if(response.status === 200){
            setCookie('token', response.data,{ path: '/', expires: new Date(Date.now() + 1000 * 60 * 60 * 24) })
            return true
          }else
            return false
        }catch(e){
          if(e.message == "Network Error"){
            alert("Server is Not Working, please try again later....!")
            return false
          }
          return false
        }
      }

    return (
        <div className="login">
            <header>
                <img src={logim} alt="" />
                <Link to="/register">
                <button>Register</button>
                </Link>
            </header>
            <div className="border">
                <div className="mainContent">
                    <div className="leftSide">
                        <img src={computer} alt="" />
                        <p>Create your profile now and be visible to the top recruiters in the world</p>
                    </div>
                    <div className="rightSide">
                        <h5>New to foundit? 
                            <Link to="/register"><span>Register</span></Link></h5>      
                        <div className="loginForm">
                            <h4>Login</h4>
                            <div className="oauth">
                                <button><img src={google} alt="" />Google</button>
                                <button><img src={linkedin} alt="" />LinkedIn</button>
                                <button><img src={fb} alt="" />Facebook</button>
                            </div>
                            <div className="or">
                                <hr />
                                <p>or</p>
                                <hr />
                            </div>
                            <form onSubmit={submit}>
                                <div className="inputs">
                                    <label htmlFor="userName"></label>
                                    <input type="text" id="userName" placeholder="Enter UserName" />
                                </div>
                                <div className="inputs">
                                    <label htmlFor="password"></label>
                                    <input type="password" id="password" placeholder="Password" />
                                </div>
                                <div className="forget">
                                    <Link to="/fpassword"><p>Forgot password?</p></Link>
                                    <button className="blog" type="submit">Login In</button>
                                </div>
                                <div className="otp">
                                    <p>Login via OTP</p>
                                </div>
                            </form>
                        </div>
                        <div className="gplay">
                            <span>Your dream job is a click away! Get the app on your mobile.</span>
                            <div>
                                <img src={gpaly} className="pay" />
                                <img src={store} className="store" />
                            </div>

                        </div>
                    </div>
                </div>
            </div>
            <div className="images">
<select>
    <option>Channai</option>
    <option>Bangaluru</option>
    <option>Coimbator</option>
</select>
<div className="contact">
    <img src={call} /><p>Toll No:+917358892432
        Toll Free No: 1-800-419666</p>
</div>
<div className="contact">
    <img src={mail} /><p>info@foundit.in</p>
</div>
</div> 
            
        </div>
    )
}

export default Login;

