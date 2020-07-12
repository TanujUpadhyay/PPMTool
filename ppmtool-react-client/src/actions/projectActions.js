import axios from "axios";
import { GET_ERRORS, GET_PROJECTS } from "./types";

export const createProject = (project, history) => async (dispatch) => {
  try {
    const res = await axios.post("http://localhost:8080/api/project", project);
    history.push("/dashboard");
    console.log(res.status);
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data,
    });
  }
};

export const getProjects = () => async (dispatch) => {
  const res = await axios.get("http://localhost:8080/api/project/all");
  console.log("after send data : ", res.status);
  dispatch({
    type: GET_PROJECTS,
    payload: res.data,
  });
};
