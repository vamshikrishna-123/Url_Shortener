import EmployeeService from "../services/EmployeeService";
import React, { Component } from "react";
// import { withRouter } from "react-router-dom";
// import { useHistory } from "react-router-dom";

import { Link } from "react-router";
// const
export default class ListEmployeeComponent extends Component {
  constructor(props) {
    super(props);
    this.state = {
      employees: [],
    };
    this.addEmployee = this.addEmployee.bind(this);
  }
  componentDidMount() {
    EmployeeService.getEmployees().then((res) => {
      this.setState({ employees: res.data });
    });
  }
  // history = useHistory();

  addEmployee() {
    this.props.history.push("/add-employee");
  }
  render() {
    return (
      <div>
        <h2 className="text-center">UrlList</h2>
        <div className="row">
          {/* <button className="btn btn-primary" onClick={this.addEmployee}>
            Add Employee
          </button> */}
        </div>
        <div className="row">
          <table className="table table-striped table-bordered">
            <thead>
              <tr>
                <th>Original URL</th>
                <th>Short Url</th>
                <th>Expiration time</th>
                {/* <th>Action </th> */}
              </tr>
            </thead>
            <tbody>
              {this.state.employees.map((employee) => (
                <tr key={employee.id}>
                  <td>{employee.originalUrl}</td>
                  <td>{employee.shortLink}</td>
                  <td>{employee.expirationDate}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    );
  }
}
//  withRouter(connect(mapStateToProps)(CustomForm));
// export default withRouter(ListEmployeeComponent);
