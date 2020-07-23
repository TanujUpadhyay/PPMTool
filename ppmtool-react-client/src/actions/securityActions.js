import axios from "axios";
import { GET_ERRORS } from "./types";

export const createNewUser = (newUser, history) => async (dispatch) => {
  try {
    const res = await axios.post("/api/users/register", newUser);
    history.push("/login");
    dispatch({
      type: GET_ERRORS,
      payload: {},
    });
    console.log("user request sending : ", res.status);
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data,
    });
  }
};
