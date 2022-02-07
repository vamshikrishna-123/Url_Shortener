import axios from "axios";

const Employee_Url = "http://localhost:8080/api/v1/generate";

class EmployeeService {
  getEmployees() {
    return axios.get(Employee_Url);
  }
  createEmployee(employee) {
    return axios.post(Employee_Url, employee);
  }
}

export default new EmployeeService();
