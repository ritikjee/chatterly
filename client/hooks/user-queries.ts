import { AutomationService } from "@/services/automation-service";
import { useQuery } from "@tanstack/react-query";

export const useQueryAutomations = () => {
  return useQuery({
    queryKey: ["user-automations"],
    queryFn: AutomationService.getAutomations,
  });
};
