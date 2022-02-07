import logo from "./logo.svg";
import "./App.css";
import ListEmployeeComponent from "./Components/ListEmployeeComponent";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
// import { Switch, Route } from "react-router";
import HeaderComponent from "./Components/HeaderComponent";
import FooterComponent from "./Components/FooterComponent";
import CreateEmployeeComponent from "./Components/CreateEmployeeComponent";
import Menus from "./Components/Menus";
import Introduction from "./Components/Introduction";

function App() {
  return (
    <div>
      <Router>
        {/* <div className="container"> */}
        {/* <HeaderComponent /> */}
        <div className="container">
          <div>
            <Menus />
          </div>
          <Routes>
            <Route path="/" element={<Introduction />} />
            <Route
              // exact
              path="/employees"
              element={<ListEmployeeComponent />}
            />
            <Route
              // exact
              path="/add-employee"
              element={<CreateEmployeeComponent />}
            />
          </Routes>
        </div>
        {/* <FooterComponent /> */}
        {/* </div> */}
      </Router>
    </div>
  );
}

export default App;
