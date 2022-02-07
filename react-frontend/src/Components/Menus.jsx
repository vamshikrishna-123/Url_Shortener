import React from "react";
import { ListGroup, ListGroupItem } from "reactstrap";
const Menus = () => {
  return (
    <ListGroup>
      <ListGroupItem tag="a" href="/" action>
        Home
      </ListGroupItem>
      <ListGroupItem tag="a" href="/add-employee" action>
        Add Url
      </ListGroupItem>
      <ListGroupItem tag="a" href="/employees" action>
        View Urls
      </ListGroupItem>
    </ListGroup>
  );
};
export default Menus;
