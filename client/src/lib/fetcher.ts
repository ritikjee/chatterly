import axios from "axios";

export const fetcher = async <T>(payload: {
  url: string;
  method?: string;
  data?: any;
  params?: any;
  headers?: any;
  responseType?: any;
  onUploadProgress?: any;
  onDownloadProgress?: any;
}) => {
  try {
    const {
      url,
      method = "GET",
      data,
      params,
      headers,
      responseType,
      onUploadProgress,
      onDownloadProgress,
    } = payload;

    const response = await axios({
      method,
      url,
      data,
      params,
      headers: {
        ...headers,
        "Content-Type": "application/json",
      },
      withCredentials: true,
      responseType,
      onUploadProgress,
      onDownloadProgress,
    });

    return {
      data: response.data?.data as T,
      error: null,
    };
  } catch (error: any) {
    return {
      data: null,
      error: {
        message: error.response?.data.message || "Something went wrong",
        status: error.response?.status || 500,
      },
    };
  }
};
