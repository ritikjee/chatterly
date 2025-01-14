import { AutomationService } from "@/services/automation-service";
import { useMutationData } from "./use-mutation-data";

export const useCreateAutomation = () => {
  const { isPending, mutate } = useMutationData(
    ["create-automation"],
    () => AutomationService.createAutomation(),
    "user-automations"
  );

  return { isPending, mutate };
};
