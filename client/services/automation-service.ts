import { fetcher } from "@/lib/fetcher";
import { Automation } from "@/types/automation";

export class AutomationService {
  static async getAutomations() {
    return await fetcher<Automation>({
      method: "GET",
      url: `${process.env.NEXT_PUBLIC_BACKEND_URL}/api/v1/automation/all`,
    });
  }
}
