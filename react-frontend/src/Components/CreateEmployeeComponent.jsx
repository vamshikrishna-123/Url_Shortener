import React, { Component } from "react";
import EmployeeService from "../services/EmployeeService";

export default class CreateEmployeeComponent extends Component {
  constructor(props) {
    super(props);
    this.state = {
      originalUrl: "",
      // shortLink: "",
      // expirationDate: "",
    };
    this.changeFirstNameHandler = this.changeFirstNameHandler.bind(this);
    // this.changeLastNameHandler = this.changeLastNameHandler.bind(this);
    // this.changeemailIdHandler = this.changeemailIdHandler.bind(this);
    this.saveEmployee = this.saveEmployee.bind(this);
  }
  saveEmployee = (e) => {
    e.preventDefault();
    let employee = {
      url: this.state.originalUrl,
      // shortLink: this.state.shortLink,
      // expirationDate: this.state.expirationDate,
    };
    console.log("employee=>" + JSON.stringify(employee));
    EmployeeService.createEmployee(employee).then((res) => {
      this.props.history.push("/employees");
    });
  };
  changeFirstNameHandler = (event) => {
    this.setState({ originalUrl: event.target.value });
  };
  // changeLastNameHandler = (event) => {
  //   this.setState({ shortLink: event.target.value });
  // };
  // changeemailIdHandler = (event) => {
  //   this.setState({ expirationDate: event.target.value });
  // };
  // cancel() {
  //   this.props.history.push("/employees");
  // }
  render() {
    return (
      <div>
        <div className="container">
          <div className="row">
            <div className="card col-md-6 offset-md-3 offset-md-3">
              <h3 className="text-center"> Add URL</h3>
              <div className="card-body">
                <form>
                  <div className="form-group">
                    <label>Enter the originalUrl</label>
                    <input
                      placeholder="URL"
                      name="firstname"
                      className="form-control"
                      value={this.state.originalUrl}
                      onChange={this.changeFirstNameHandler}
                    />
                  </div>
                  {/* <div className="form-group">
                    <label> Last Name</label>
                    <input
                      placeholder="LastName"
                      name="lastname"
                      className="form-control"
                      value={this.state.shortLink}
                      onChange={this.changeLastNameHandler}
                    /> */}
                  {/* </div> */}
                  {/* <div className="form-group">
                    <label> Email Id</label>
                    <input
                      placeholder="EmailId"
                      name="emailid"
                      className="form-control"
                      value={this.state.expirationDate}
                      onChange={this.changeemailIdHandler}
                    />
                  </div> */}
                  <button
                    className="btn btn-success"
                    onClick={this.saveEmployee}
                  >
                    {" "}
                    GO
                  </button>
                  {/* <button
                    className="btn btn-danger"
                    onClick={this.cancel.bind(this)}
                    style={{ marginleft: "10px" }}
                  >
                    Cancel
                  </button> */}
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}
