
import Header from "../components/header";
import Search from "./search";
import Company from "./company";
import Categories from "./categories";
import Benner from "./benner";
import Footer from "./footer";
import Contant from "./constant";

function Home(){
    return (
        <div>
            
            <Header />
            <Search />
            <Contant/>
            <Company/>
            <Categories/>
            <Benner/>
            <Footer/>
        </div>
    )
}

export default Home;