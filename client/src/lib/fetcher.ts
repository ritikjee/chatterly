import axios from "axios";

const getCookie = (key: string): string | null => {
  if (typeof window === "undefined") {
    const { cookies } = require("next/headers");
    return cookies().get(key)?.value || null;
  } else {
    const match = document.cookie.match(new RegExp(`(^| )${key}=([^;]+)`));
    return match ? decodeURIComponent(match[2]) : null;
  }
};

export const fetcher = async <T>(payload: {
  url: string;
  method?: string;
  data?: any;
  params?: any;
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
        "Content-Type": "application/json",
        Cookie: `token=${getCookie("token")}`,
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
