import React from "react";
import ProjectItem from "./Project/ProjectItem";
import CreateProjectButton from "./Project/CreateProjectButton";

function DashBoard() {
  return (
    <div className="projects">
      <div className="container">
        <div className="row">
          <div className="col-md-12">
            <h1 className="display-4 text-center">Projects</h1>
            <br />

            <CreateProjectButton />

            <br />
            <hr />
          </div>
          <ProjectItem />
        </div>
      </div>
    </div>
  );
}

export default DashBoard;
