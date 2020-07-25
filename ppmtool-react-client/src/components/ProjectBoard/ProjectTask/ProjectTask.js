import React, { Component } from "react";
import { Link } from "react-router-dom";
import { deleteProjectTask } from "../../../actions/backlogAction";
import PropTypes from "prop-types";
import { connect } from "react-redux";

class ProjectTask extends Component {
  onDeleteClik(backlog_id, pt_id) {
    this.props.deleteProjectTask(backlog_id, pt_id);
  }

  render() {
    const { project_task } = this.props;

    let priorityString;
    let priorityClass;

    if (project_task.priority === 1) {
      priorityClass = "bg-dark text-light";
      priorityString = " HIGH ";
    }

    if (project_task.priority === 2) {
      priorityClass = "bg-info text-dark";
      priorityString = " MEDIUM ";
    }

    if (project_task.priority === 3) {
      priorityClass = "bg-light text-dark";
      priorityString = " LOW ";
    }

    return (
      <div className="card border border-danger mb-3 bg-gray">
        <div className={`card-header text-primary ${priorityClass}`}>
          ID: {project_task.projectSequence} -- Priority:{priorityString}
        </div>
        <div className="card-body bg-secondary text-warning">
          <h5 className="card-title">{project_task.summary}</h5>
          <p className="card-text text-truncate ">
            {project_task.acceptanceCriterial}
          </p>
          <Link
            to={`/updateProjectTask/${project_task.projectIdentifier}/${project_task.projectSequence}`}
            className="btn btn-light btn-outline-dark"
          >
            View / Update
          </Link>

          <button
            className="btn btn-danger  ml-4"
            onClick={this.onDeleteClik.bind(
              this,
              project_task.projectIdentifier,
              project_task.projectSequence
            )}
          >
            Delete
          </button>
        </div>
        <p className="card-text p-1 ml-2">
          Last update : {project_task.update_At}
        </p>
      </div>
    );
  }
}

ProjectTask.propTypes = {
  deleteProjectTask: PropTypes.func.isRequired,
};

export default connect(null, { deleteProjectTask })(ProjectTask);
