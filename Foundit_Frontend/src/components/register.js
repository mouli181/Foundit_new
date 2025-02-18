import "../style/register.css"
import contact from"../images/contact.png"
import { Link, useNavigate } from "react-router-dom"
import axios from 'axios'

function Register(){

    const Navigate = useNavigate()

    const submit = async (event) => {
        event.preventDefault()
        var registerData = {
          userName: event.target[0].value,
          email: event.target[1].value,
          password: event.target[2].value,
          contact: event.target[3].value
        }
        if(validation(registerData)){
            if(await addUser(registerData)){
                alert(" Register SuccessFully....! ")
                Navigate('/login')
            }
        }else {
            alert("please Enter the Valid Input......!")
        }
    }

    function validation(registerData) { 
        const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/
        const passwordPattern = /^.{8,}$/
        if(registerData.userName.length < 2){
            return false
        }else if(!passwordPattern.test(registerData.password)){
            return false
        }else if(!emailPattern.test(registerData.email)){
            return false
        }else if(registerData.contact.length != 10){
            return false
        }else{
            return true
        }
      }

      async function addUser(registerData) {
        try{
          let response = await axios.post('http://localhost:8888/register',
            {
                userName: registerData.userName,
                email: registerData.email,
                password: registerData.password,
                contact: registerData.contact
            })
            if(response.status === 201 && response.data === "Registered Successfully...!"){
              return true
            }
        }catch (e){
            if(e.message != "Network Error"){
                if(e.response.status === 409 && e.response.data === 'That UserName is taken, Try another...!'){
                    alert("That UserName is taken, Try another...!")
                    return false
                }else { 
                    alert("Something went wrong, please try again later....!")
                    return false
                }
            }
            alert("Server is Not Working, please try again later....!")
            return false
        }
      }

    return (
        <div className="regis">
        <div className="reg">
            <div className="logimg">
                <Link to="/login">
                <img src={contact} />
                   Login</Link>
            </div>
        </div>
        <div className="Regesterr">
                <form onSubmit={submit}>
                    <label>Name : </label><input type="name" placeholder="Enter Your Name"></input>
                    <label>Mail id : <input type="email" placeholder="Enter Your Name"></input></label>
                    <label>Password : <input type="password" placeholder="Enter Your Password"></input></label>
                    <label>contact : <input type="number" placeholder="Enter a Phone Number"></input></label>
                    <button type="submit">Register</button>
                    <Link to="/fpassword"><p>Forget Password</p></Link>
                </form>
            </div>
        </div>
    )
}

export default Register

