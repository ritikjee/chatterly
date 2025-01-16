import { fetcher } from "@/lib/fetcher";
import { Automation } from "@/types/automation";

export class AutomationService {
  static async getAutomations() {
    return await fetcher<Automation[]>({
      method: "GET",
      url: `${process.env.NEXT_PUBLIC_BACKEND_URL}/api/v1/automation/all`,
    });
  }

  static async createAutomation() {
    return await fetcher({
      method: "POST",
      url: `${process.env.NEXT_PUBLIC_BACKEND_URL}/api/v1/automation/create`,
    });
  }

  static async getAutomationById(id: string) {
    return await fetcher<Automation>({
      method: "GET",
      url: `${process.env.NEXT_PUBLIC_BACKEND_URL}/api/v1/automation/${id}`,
    });
  }

  static async updateAutomation(
    id: string,
    data: { name?: string; active?: boolean }
  ) {
    return await fetcher({
      method: "PUT",
      url: `${process.env.NEXT_PUBLIC_BACKEND_URL}/api/v1/automation/update/${id}`,
      params: data,
    });
  }

  static async activateAutomation(id: string, state: boolean) {
    return await fetcher({
      method: "PUT",
      url: `${process.env.NEXT_PUBLIC_BACKEND_URL}/api/v1/automation/activate/${id}`,
      params: { state },
    });
  }

  static async saveListener(
    automationId: string,
    listener: "SMARTAI" | "MESSAGE",
    prompt: string,
    reply?: string
  ) {
    return await fetcher({
      method: "POST",
      url: `${process.env.NEXT_PUBLIC_BACKEND_URL}/api/v1/automation/listener/create-listener`,
      data: {
        automationId,
        listener,
        prompt,
        reply,
      },
    });
  }

  static async saveTrigger(automationId: string, trigger: string[]) {
    return await fetcher({
      method: "POST",
      url: `${process.env.NEXT_PUBLIC_BACKEND_URL}/api/v1/automation/trigger/create`,
      data: {
        automationId,
        triggers: trigger,
      },
    });
  }

  static async saveKeyword(automationId: string, keyword: string) {
    return await fetcher({
      method: "POST",
      url: `${process.env.NEXT_PUBLIC_BACKEND_URL}/api/v1/automation/keyword/create`,
      data: {
        automationId,
        keywordId: keyword,
      },
    });
  }

  static async deleteKeyword(automationId: string, keywordId: string) {
    return await fetcher({
      method: "DELETE",
      url: `${process.env.NEXT_PUBLIC_BACKEND_URL}/api/v1/automation/keyword/delete`,
      data: {
        automationId,
        keywordId,
      },
    });
  }

  static async addPost(
    automationId: string,
    posts: {
      postid: string;
      caption?: string;
      media: string;
      mediaType: "IMAGE" | "VIDEO" | "CAROSEL_ALBUM";
    }[]
  ) {
    return await fetcher({
      method: "POST",
      url: `${process.env.NEXT_PUBLIC_BACKEND_URL}/api/v1/automation/post/create`,
      data: {
        automationId,
        posts,
      },
    });
  }
}
