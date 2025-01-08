import { fetcher } from "@/lib/fetcher";

export class AuthService {
  static async login(data: { email: string; password: string }) {
    return await fetcher({
      url: `${process.env.NEXT_PUBLIC_BACKEND_URL}/api/v1/auth/login`,
      method: "POST",
      data,
    });
  }

  static async register(data: { email: string; password: string }) {
    return await fetcher({
      url: `${process.env.NEXT_PUBLIC_BACKEND_URL}/api/v1/auth/register`,
      method: "POST",
      data,
    });
  }

  static async verifyAccount(data: { email: string; token: string }) {
    return await fetcher({
      url: `${process.env.NEXT_PUBLIC_BACKEND_URL}/api/v1/auth/verify-account`,
      method: "POST",
      params: data,
    });
  }

  static async logout() {
    return await fetcher({
      url: `${process.env.NEXT_PUBLIC_BACKEND_URL}/api/v1/user/logout`,
      method: "POST",
    });
  }

  static async getAuthenticatedUser() {
    return await fetcher({
      url: `${process.env.NEXT_PUBLIC_BACKEND_URL}/api/v1/user/me`,
      method: "GET",
    });
  }
}
