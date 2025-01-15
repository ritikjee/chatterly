import { fetcher } from "@/lib/fetcher";
import { User } from "@/types/user";

export class UserService {
  static async getAuthenticatedUser() {
    return await fetcher<User>({
      url: `${process.env.NEXT_PUBLIC_BACKEND_URL}/api/v1/user/me`,
      method: "GET",
    });
  }
}
