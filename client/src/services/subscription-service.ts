import { fetcher } from "@/lib/fetcher";
import { SUBSCRIPTION } from "@/types/subscription";

export class SubscriptionService {
  static async getSubscription() {
    return await fetcher<SUBSCRIPTION>({
      method: "GET",
      url: `${process.env.NEXT_PUBLIC_BACKEND_URL}/api/v1/subscription`,
    });
  }
}
