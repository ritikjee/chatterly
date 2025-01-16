import { fetcher } from "@/lib/fetcher";
import { INSTAGRAM_POSTS, INTEGRATIONS } from "@/types/integration";

export class IntegrationService {
  static async getIntegrations() {
    return await fetcher<INTEGRATIONS>({
      method: "GET",
      url: `${process.env.NEXT_PUBLIC_BACKEND_URL}/api/v1/integration`,
    });
  }

  static async getInstagramPosts() {
    return await fetcher<INSTAGRAM_POSTS[]>({
      method: "GET",
      url: `${process.env.NEXT_PUBLIC_BACKEND_URL}/api/v1/integration/instagram/get-posts`,
    });
  }
}
