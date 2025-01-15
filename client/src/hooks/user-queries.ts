import { AutomationService } from "@/services/automation-service";
import { SubscriptionService } from "@/services/subscription-service";
import { UserService } from "@/services/user-service";
import { useQuery } from "@tanstack/react-query";

export const useQueryAutomations = () => {
  return useQuery({
    queryKey: ["user-automations"],
    queryFn: () => AutomationService.getAutomations(),
  });
};

export const useQueryAutomation = (id: string) => {
  return useQuery({
    queryKey: ["automation-info"],
    queryFn: () => AutomationService.getAutomationById(id),
  });
};

export const useQueryUser = () => {
  return useQuery({
    queryKey: ["user-profile"],
    queryFn: () => UserService.getAuthenticatedUser,
  });
};

export const useSubscription = () => {
  return useQuery({
    queryKey: ["user-subscription"],
    queryFn: () => SubscriptionService.getSubscription,
  });
};
