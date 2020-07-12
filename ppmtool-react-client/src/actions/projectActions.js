import axios from "axios";
import { GET_ERRORS, GET_PROJECTS, GET_PROJECT, DELETE_PROJECT } from "./types";

export const createProject = (project, history) => async (dispatch) => {
  try {
    const res = await axios.post("/api/project", project);
    history.push("/dashboard");
    dispatch({
      type: GET_ERRORS,
      payload: {},
    });
    console.log("after creating : ", res.status);
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data,
    });
  }
};

export const getProjects = () => async (dispatch) => {
  const res = await axios.get("/api/project/all");
  console.log("after geting all : ", res.status);
  dispatch({
    type: GET_PROJECTS,
    payload: res.data,
  });
};

export const getProject = (id, history) => async (dispatch) => {
  try {
    const res = await axios.get(`/api/project/${id}`);
    dispatch({
      type: GET_PROJECT,
      payload: res.data,
    });
    console.log("after geting one : ", res.status);
  } catch (err) {
    history.push("/dashboard");
  }
};

export const deleteProject = (id) => async (dispatch) => {
  if (
    window.confirm(
      "Are you sure?\nthis will delete the project and all the data related to it!!"
    )
  ) {
    const res = await axios.delete(`/api/project/${id}`);
    dispatch({
      type: DELETE_PROJECT,
      payload: id,
    });
    console.log("after deleting : ", res.status);
  }
};
