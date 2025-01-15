import { fetcher } from "@/lib/fetcher";
import { INTEGRATIONS } from "@/types/integration";

export class IntegrationService {
  static async getIntegrations() {
    return await fetcher<INTEGRATIONS>({
      method: "GET",
      url: `${process.env.NEXT_PUBLIC_BACKEND_URL}/api/v1/integration`,
    });
  }
}
