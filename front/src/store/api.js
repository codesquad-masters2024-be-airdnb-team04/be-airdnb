import axios from "axios";
import { router } from "tinro";

const send = async ({
  method = "",
  path = "",
  data = {},
  access_token = "",
} = {}) => {
  const commonUrl = "http://localhost:8080";
  const url = commonUrl + path;

  const headers = {
    "content-type": "application/json;charset=UTF-8",
    accept: "application/json,",
    // Authorization: access_token ? `Bearer ${access_token}` : "",
  };

  const options = {
    method,
    url,
    headers,
    data,
    withCredentials: true,
  };

  try {
    const response = await axios(options);
    console.log("res", response);
    return {
      status: response.status,
      data: response.data,
    };
  } catch (error) {
    if (error.response.status === 401) {
      console.log("401 error");
      router.goto("/login");
    } else if (error.response.status === 404) {
      console.log("404 error");
      router.goto("/notFound");
    } else if (error.response.status === 302) {
      console.log("302 error");
      router.goto("/login");
    } else {
      alert(error.response.data.errorMessage);
    }
  }
};

const getApi = ({ path = "", access_token = "" } = {}) => {
  return send({ method: "GET", path: path });
};

const putApi = ({ path = "", data = {}, access_token = "" } = {}) => {
  return send({
    method: "PUT",
    path: path,
    data: data,
    // access_token: access_token,
  });
};

const patchApi = ({ path = "", data = {}, access_token = "" } = {}) => {
  return send({
    method: "PATCH",
    path: path,
    data: data,
    // access_token: access_token,
  });
};

const postApi = ({ path = "", data = {}, access_token = "" } = {}) => {
  return send({
    method: "POST",
    path: path,
    data: data,
    // access_token: access_token,
  });
};

const delApi = ({ path = "", data = {}, access_token = "" } = {}) => {
  return send({
    method: "DELETE",
    path: path,
    data: data,
    // access_token: access_token,
  });
};

export { getApi, putApi, patchApi, postApi, delApi };
