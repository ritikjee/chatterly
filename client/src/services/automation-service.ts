import { fetcher } from "@/lib/fetcher";
import { Automation } from "@/types/automation";

export class AutomationService {
  static async getAutomations(token?: string) {
    return await fetcher<Automation[]>({
      method: "GET",
      url: `${process.env.NEXT_PUBLIC_BACKEND_URL}/api/v1/automation/all`,
      headers: {
        Cookie: `token=${token}`,
      },
    });
  }

  static async createAutomation() {
    return await fetcher<Automation>({
      method: "POST",
      url: `${process.env.NEXT_PUBLIC_BACKEND_URL}/api/v1/automation/create`,
    });
  }
}
