import axios from "axios";

const Api = axios.create({
  baseURL: import.meta.env.VITE_API_URL,
});

Api.interceptors.request.use(async (config) => {
  const accessToken = await localStorage.getItem("authToken");
  if (accessToken) {
    config.headers.Authorization = `Bearer ${accessToken}`;
  }

  return config;
});

Api.interceptors.response.use(
  response => {
    return response;
  },
  async error => {
    if (error.response && error.response.status === 401) {
      await localStorage.removeItem('authToken');
    }
    return Promise.reject(error);
  }
);

export default Api;
