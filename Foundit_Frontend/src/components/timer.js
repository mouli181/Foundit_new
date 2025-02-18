import { useEffect, useState } from "react"
function Timer (){
  const  [user,setuser] =useState(0)
  useEffect (()=>{
    document.title = `Click Count ${user} value`
  })
  return(
    <div>
      <h3>Count {user}</h3>
      <button onClick={()=>{setuser (user +1)}}>Ingrement</button>
      <button onClick={()=>{setuser (user -1)}}>Decrement</button>
      <button onClick={()=>{setuser (0)}}>Reset</button>
    </div>
  )
}
export default Timer


// function Timer (props){
//   return(
//     <div>
//       <p>{props.message}</p>
//     </div>
//   )
// }
// export default Timer