import { useSubscription } from "@/hooks/user-queries";

type Props = {
  type: "FREE" | "PRO";
  children: React.ReactNode;
};

export const SubscriptionPlan = ({ children, type }: Props) => {
  const { data } = useSubscription();
  return data?.data?.plan === type && children;
};
